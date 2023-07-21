# import LiveSpeech
from pocketsphinx import LiveSpeech


# an for in loop to iterate in speech
for phrase in LiveSpeech(dict='res/speech/dictionary.dict', jsgf='res/speech/grammar.jsgf'):
    print(phrase, flush=True)

#for phrase in LiveSpeech():
    # here the result is stored in phrase which
    # ultimately displays all the words recognized
#    print(phrase)
#else:
#    print("Sorry! could not recognize what you said")