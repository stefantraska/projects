#  A simple pong game

from turtle import Screen
from paddle import Paddle
from ball import Ball
from scoreboard import Scoreboard
import time

screen = Screen()

screen.bgcolor("black")
screen.setup(width=800, height=600)
screen.title("Pong")
screen.tracer(0)

l_paddle = Paddle(-350)
r_paddle = Paddle(350)
ball = Ball()
scoreboard = Scoreboard()

screen.listen()
screen.onkey(l_paddle.go_up, "w")
screen.onkey(l_paddle.go_down, "s")
screen.onkey(r_paddle.go_up, "Up")
screen.onkey(r_paddle.go_down, "Down")

game_is_running = True
while game_is_running:
    time.sleep(0.1)
    screen.update()
    ball.move()

    if ball.ycor() > 295 or ball.ycor() < -295:
        ball.bounce_wall()

    if (ball.distance(r_paddle) < 50 and ball.xcor() > 320) or (ball.distance(l_paddle) < 50 and ball.xcor() < -320):
        ball.bounce_paddle()

    if ball.xcor() > 370:
        ball.reset_position()
        scoreboard.r_point()

    if ball.xcor() < -370:
        ball.reset_position()
        scoreboard.l_point()

screen.exitonclick()