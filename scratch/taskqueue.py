import threading
import Queue
import time 
import sched 

task_queue = Queue.Queue(maxsize=0) 

def db_insert_worker():
    while not task_queue.empty():
        task = task_queue.get()
        print task 
        ##call the db insert here 
        task_queue.task_done() 

def get_data_from_aurdino():
    pass 

if __name__ == "__main__":
    while True:
        buffer = get_data_from_aurdino()
        threading.Timer(1.0,db_insert_worker)