from turtle import Turtle
import random

FONT = ("Courier", 24, "normal")
ALIGN = "center"

class Scoreboard(Turtle):

    def __init__(self):
        super().__init__()
        self.color("white")
        self.penup()
        self.hideturtle()
        self.goto(0, 270)
        self.score = 0
        with open("score.txt", mode="r") as file:
            self.high_score = int(file.read())
        self.update_scoreboard()

    def score_change(self):
        self.score = self.score + 1
        self.update_scoreboard()

    def update_scoreboard(self):
        self.clear()
        self.write(arg="Score: " + str(self.score) + "   High Score: " + str(self.high_score), align=ALIGN, font=FONT)

    def reset_game(self):
        if self.score > self.high_score:
            self.high_score = self.score
        self.score = 0

        with open("score.txt", mode="w") as file:
            file.write(f"{self.high_score}")
        self.update_scoreboard()