from turtle import Turtle


class Ball(Turtle):

    def __init__(self):
        super().__init__()
        self.shapesize(stretch_wid=1, stretch_len=1)
        self.goto(0, 0)
        self.penup()
        self.shape("circle")
        self.color("orange")
        self.x_direction = 10
        self.y_direction = 10

    def move(self):
        self.goto(self.xcor() + self.x_direction, self.ycor() + self.y_direction)

    def bounce_wall(self):
        self.y_direction *= -1

    def bounce_paddle(self):
        self.x_direction *= -1

    def reset_position(self):
        self.goto(0, 0)
        self.bounce_paddle()
