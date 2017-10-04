
import os
from os import path 
import random 
import threading

class FileGen(object):

    """
    This module generates and updates
    """
    content_lines  = [
        'The quick brown fox jumped over the lazy dog',
        'The fox got lazy',
        'The quick brown dog jumped over the hen',
        'The dog got lazy',
        'The quick borwn hem jumped over the pig'
        'The pig got lazy',
        'Now all lazys are sleeping'
    ]
    thread_stop_event = threading.Event() 
    NUMBER_OF_FILES=1000

    def __init__(self, root):
        self.root = root 
        if not os.access(self.root, os.W_OK):
            raise ValueError("Cannot find or write to the root:{0}".format(self.root))
        self._generate_files() 
        FileGen.thread_stop_event.clear()

    def _get_random_content(self) :
        return random.Random().choice(FileGen.content_lines)

    def _generate_files(self):
        file_name_prefix = FileGen.__name__
        for i in range(FileGen.NUMBER_OF_FILES):
            file_name = file_name_prefix + str(i)
            file_path = path.join(self.root,file_name)
            lines = []
            with open(file_path,"w+") as f:
                for j in range(10):
                    lines.append(self._get_random_content())
                f.writelines(lines)

    def filegen_thread(self):
        while FileGen.thread_stop_event.isSet():
            walker = os.walk(self.root)
            for r,d,f in walker:
                for fn in f:
                    with open(path.join(self.root,fn),"w+") as fd:
                        fd.write(self._get_random_content() + "\n")
            