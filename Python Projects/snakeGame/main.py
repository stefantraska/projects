# A simple snake game

from turtle import Screen
from food import Food
import time
from snake import Snake
from scoreboard import Scoreboard

screen = Screen()
screen.setup(width=600, height=600)
screen.bgcolor("black")
screen.title("Snake")
screen.tracer(0)

snake = Snake()
food = Food()
scoreboard = Scoreboard()

screen.listen()
screen.onkey(snake.up, "Up")
screen.onkey(snake.down, "Down")
screen.onkey(snake.left, "Left")
screen.onkey(snake.right, "Right")

game_is_running = True
while game_is_running:
    screen.update()
    time.sleep(0.08)
    snake.move()

    if snake.segments[0].distance(food) < 15:
        food.new_location()
        scoreboard.score_change()
        snake.extend()

    if snake.segments[0].xcor() > 285 or snake.segments[0].xcor() < -285 or snake.segments[0].ycor() > 285 or snake.segments[0].ycor() < -285:
        scoreboard.reset_game()
        snake.reset()

    for segment in snake.segments[1:]:
        if snake.segments[0].distance(segment) < 10:
            scoreboard.reset_game()
            snake.reset()


scoreboard.game_over()

screen.exitonclick()