# import LiveSpeech
from pocketsphinx import AudioFile

import sys
from sys import byteorder
from array import array
from struct import pack

import pyaudio
import os
import wave

from pydub import AudioSegment


from contextlib import redirect_stderr

with open('filename.log', 'w') as stderr, redirect_stderr(stderr):
    a=1#stops error messages from printing. probably need to modify this

THRESHOLD = 1200
TRIM_THRESHOLD = THRESHOLD * .75
CHUNK_SIZE = 1024
FORMAT = pyaudio.paInt16
RATE = 16000
wasSilent = True
processing = False
path="C:\\Users\\Pbaro\\OneDrive\\Desktop\\Fun\\Programming\\JaPlanner\\res\\scripts\\python\\speech\\temp\\"

def is_silent(snd_data):
    "Returns 'True' if below the 'silent' threshold"
    return max(snd_data) < THRESHOLD

def normalize(snd_data):
    "Average the volume out"
    MAXIMUM = 16384
    times = float(MAXIMUM)/max(abs(i) for i in snd_data)

    r = array('h')
    for i in snd_data:
        r.append(int(i*times))
    return r

def trim(snd_data):
    "Trim the blank spots at the start and end"
    def _trim(snd_data):
        snd_started = False
        r = array('h')

        for i in snd_data:
            if not snd_started and abs(i)>THRESHOLD:
                snd_started = True
                r.append(i)

            elif snd_started:
                r.append(i)
        return r

    # Trim to the left
    snd_data = _trim(snd_data)

    # Trim to the right
    snd_data.reverse()
    snd_data = _trim(snd_data)
    snd_data.reverse()
    return snd_data

def add_silence(snd_data, seconds):
    "Add silence to the start and end of 'snd_data' of length 'seconds' (float)"
    silence = [0] * int(seconds * RATE)
    r = array('h', silence)
    r.extend(snd_data)
    r.extend(silence)
    return r

def record():
    global wasSilent
    """
    Record a word or words from the microphone and 
    return the data as an array of signed shorts.

    Normalizes the audio, trims silence from the 
    start and end, and pads with 0.5 seconds of 
    blank sound to make sure VLC et al can play 
    it without getting chopped off.
    """
    p = pyaudio.PyAudio()
    stream = p.open(format=FORMAT, channels=1, rate=RATE,
        input=True, output=True,
        frames_per_buffer=CHUNK_SIZE)

    num_silent = 0
    snd_started = False

    r = array('h')

    while 1:
        # little endian, signed short
        snd_data = array('h', stream.read(CHUNK_SIZE))
        if byteorder == 'big':
            snd_data.byteswap()
        r.extend(snd_data)

        silent = is_silent(snd_data)
        if (not silent and wasSilent):
            wasSilent = False

        if silent and snd_started:
            num_silent += 1
        elif not silent and not snd_started:
            snd_started = True
        if snd_started and num_silent > 30:
            break

    sample_width = p.get_sample_size(FORMAT)
    stream.stop_stream()
    stream.close()
    p.terminate()

    r = normalize(r)
    r = trim(r)
    r = add_silence(r, 0.5)
    return sample_width, r

def record_to_file(path):
    global processing
    if (processing):
        return
    "Records from the microphone and outputs the resulting data to 'path'"
    sample_width, data = record()
    data = pack('<' + ('h'*len(data)), *data)

    wf = wave.open(path, 'wb')
    wf.setnchannels(1)
    wf.setsampwidth(sample_width)
    wf.setframerate(RATE)
    wf.writeframes(data)
    wf.close()

def strip(stringlist):
    ret = ''
    for string in stringlist:
        ret += string+" "
    return ret.strip()

if __name__ == '__main__':
    print("<Starting>", flush=True)
    done = False
    while not done:
        print("<Listening>", flush=True)
        record_to_file(path+'microphone.wav')
        sound = AudioSegment.from_wav(path+'microphone.wav')  # can do same for mp3 and other formats
        raw = sound._data  # returns byte string 
        with open(path+"microphone.raw", "wb") as binary_file:
            binary_file.write(raw)
            binary_file.close()
        processing = True
        wasSilent = True
        os.remove(path+'microphone.wav')
        result = []
        for phrase in AudioFile(path+'microphone.raw', dict=path+'dictionary.dict', jsgf=path+'grammar.jsgf'):
            result.append(str(phrase))
        result = strip(result)
        os.remove(path+'microphone.raw')
        if (len(result) == 0):
            print("<None>", flush=True)
            processing = False
            break
        print(result, flush=True)
        processing = False
        print("<Done>", flush=True)
        done = True

#debug = True;

#try:
#    
#except:
#    if (debug):
#        print("Speech Recognition Error")

#for phrase in LiveSpeech():
    # here the result is stored in phrase which
    # ultimately displays all the words recognized
#    print(phrase)
#else:
#    print("Sorry! could not recognize what you said")