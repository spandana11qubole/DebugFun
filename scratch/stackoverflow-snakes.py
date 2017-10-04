import _tkinter as tkinter 
from tkinter import *

import tkinter.simpledialog

import threading


import random

dice_one_num=0
Snakes = [47,40,   39,34,   28,13]
Ladders = [3,8,   6,26,   14,22,   32,48]
player_one_pos=0
player_one_moves=0
player_one_bites=0
player_one_climb=0
player_one_name=""
player_two_name=""
def Move_One_Player():

    global player_one_pos
    global dice_one_num
    global player_one_moves
    global player_one_bites
    global player_one_climb
    global grid_one_array
    global PlayerOneMovesLabel
    global diceOneRollLabel

    if player_one_pos==49:
        player_one_pos=0
        player_one_moves=0
        player_one_pos=0
        grid_array[49-1].config(bg="white")

    old_player_one_pos=player_one_pos
    new_player_one_pos=player_one_pos+dice_one_num



    if new_player_one_pos>49:
        new_player_one_pos=49-(new_player_one_pos-49)

    for idx,val in enumerate(Snakes):

        if idx % 2 == 0:

            if new_player_one_pos==Snakes[idx]:

                new_player_one_pos=Snakes[idx+1]

                additional_message="Snake  |  "

    for idx,val in enumerate(Ladders):
        if idx % 2 == 0:
            if new_player_one_pos==Ladders[idx]:
                new_player_one_pos=Ladders[idx+1]
                additional_message="Ladder  |  "

    if old_player_one_pos>0:
        grid_array[old_player_one_pos-1].config(bg="white")

    grid_array[new_player_one_pos-1].config(bg="yellow")

    player_one_pos=new_player_one_pos
    player_one_moves=player_one_moves+1

    if player_one_pos ==49:
        PlayerOneMovesLabel['text'] = "Game won in " + str(player_one_moves)+ " moves"
        tkinter.messagebox.showinfo("Game won", player_one_name+" won the game in "+str(player_one_moves))
    else:
        PlayerOneMovesLabel['text']= "Move: " + str(player_one_moves) 


def Move_Two_Player():

    global player_two_pos
    global dice_two_num
    global player_two_moves
    global player_two_bites
    global player_two_climb
    global grid_two_array
    global PlayerTwoMovesLabel
    global diceTwoRollLabel

    if player_two_pos==49:
        player_two_pos=0
        player_two_moves=0
        player_two_pos=0
        grid_array[49-1].config(bg="white")

    old_player_two_pos=player_two_pos
    new_player_two_pos=player_two_pos+dice_two_num


    if new_player_two_pos>49:
        new_player_two_pos=49-(new_player_two_pos-49)

    for idx,val in enumerate(Snakes):

        if idx % 2 == 0:

            if new_player_two_pos==Snakes[idx]:

                new_player_two_pos=Snakes[idx+1]

                additional_message="Snake  |  "

    for idx,val in enumerate(Ladders):
        if idx % 2 == 0:
            if new_player_two_pos==Ladders[idx]:
                new_player_two_pos=Ladders[idx+1]
                additional_message="Ladder  |  "

    if old_player_two_pos>0:
        grid_array[old_player_two_pos-1].config(bg="white")

    grid_array[new_player_two_pos-1].config(bg="yellow")

    player_two_pos=new_player_two_pos
    player_two_moves=player_two_moves+1

    if player_two_pos ==49:
        PlayerTwoMovesLabel['text'] = "Game won in " + str(player_two_moves)+ " moves"
        tkinter.messagebox.showinfo("Game won", player_two_name+" won the game in "+str(player_two_moves))
    else:
        PlayerTwoMovesLabel['text']= "Move: " + str(player_two_moves) 

def rollonesDice():
    global dice_one_num
    global diceOneRollLabel
    dice_one_num=random.randint(1,6)+ random.randint(1,6)

    diceOneRollLabel['text']=player_one_name + " Rolled: " + str(dice_one_num)
    Move_One_Player()

def rolltwosDice():
    global dice_two_num
    global diceTwoRollLabel
    dice_two_num=random.randint(1,6)+ random.randint(1,6)

    diceTwoRollLabel['text']=player_two_name + " Rolled: " + str(dice_two_num)
    Move_two_Player()

def createGUI():
    global grid_array
    global PlayerOneMovesLabel
    global diceOneRollLabel
    global diceWindow
    global player_one_name
    global player_two_name

    diceWindow = Tk()

    diceWindow.title("Snakes and Ladders")
    diceWindow.resizable(width=False, height=False)
    diceWindow.config(bg='white')

    RevertLogoImage = Label(diceWindow, text="Snake and Ladders",bg='white',font=("Arial",20))
    RevertLogoImage.grid(row=0, column=1, columnspan=7)

    PlayerOneMovesLabel = Label(diceWindow, text="Please enter your name in popup window", bg='white')
    PlayerOneMovesLabel.grid(row=1,column=1,columnspan=5)

    PlayerTwoMovesLabel = Label(diceWindow, text="Please enter your name in popup window", bg='white')
    PlayerTwoMovesLabel.grid(row=1,column=1,columnspan=5)

    btnRoll = Button(diceWindow, text="Roll", command=rollonesDice or rolltwosDice, width=30)

    btnRoll.grid(row=103,column=1,columnspan=5)

    grid_array = []
    for y in range (0,7):
        for x in range (0,7):
            array_num=((x+1)+(y*7))
            grid_array.append(Label(diceWindow, borderwidth=8,text=  array_num  ))

            xx=x
            yy=y
            yy=abs(yy-7)
            if not yy % 2:
                xx=abs(xx-6)

            grid_array[array_num - 1].grid(row=  (yy+4),column=(xx+0))

            grid_array[array_num - 1].config(bg='white')

            if array_num in Snakes:

                if Snakes.index(array_num) % 2 == 0:

                    grid_array[array_num-1].config(fg="red")
                else:

                    grid_array[array_num-1].config(fg="red")

            if array_num in Ladders:

                if Ladders.index(array_num) % 2 == 0:

                    grid_array[array_num-1].config(fg="blue")
                else:
                    grid_array[array_num-1].config(fg="blue")

    while player_one_name=='' or player_one_name is None or not re.match("^[A-z]*$", player_one_name):
        player_one_name=tkinter.simpledialog.askstring("Player One Name","Please enter player one's name: ")
        if not re.match("^[A-z]*$", player_one_name):
            print("Error! Only letters A-Z allowed!")

    while player_two_name=='' or player_two_name is None or not re.match("^[A-z]*$", player_two_name):
        player_two_name=tkinter.simpledialog.askstring("Player Two Name","Please enter player two's name: ")
        if not re.match("^[A-z]*$", player_two_name):
            print("Error! Only letters A-Z allowed!") 
        PlayerOneMovesLabel['text']='- Waiting for first Roll -'
    diceOneRollLabel = Label(diceWindow, bg="white", text="Welcome "+player_one_name+", Please roll your dice!")
    diceOneRollLabel.grid(row=2,column=1,columnspan=10)
    diceWindow.mainloop()

createGUI()