from gtts import gTTS
  
# This module is imported so that we can 
# play the converted audio
import sys
from pydub import AudioSegment
#path="C:\\Users\\Pbaro\\OneDrive\\Desktop\\Fun\\Programming\\JaPlanner\\res\\scripts\\python\\speech\\"
path="res/scripts/python/speech/temp/"
import os

def listToString(s):#skip filename
    str = ""
    for i in range(1, len(s)):
        str += s[i] + " "
    if(len(str) == 0):
        return "no text"
    return str


# The text that you want to convert to audio
mytext = listToString(sys.argv).strip()
  
# Language in which you want to convert
language = 'en'

# Passing the text and language to the engine, 
# here we have marked slow=False. Which tells 
# the module that the converted audio should 
# have a high speed
myobj = gTTS(text=mytext, lang=language, slow=False)

mytext = mytext.replace(" ", "")

# Saving the converted audio in a mp3 file named
# welcome                                                                
src = path + mytext + ".mp3"
myobj.save(src)

#convert mp3 to wav
import subprocess
proc = subprocess.call(['ffmpeg', '-i', src,
                   path + mytext + ".wav"])