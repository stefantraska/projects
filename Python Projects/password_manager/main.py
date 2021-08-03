# This program takes in username and password data for each website given and stores it

import json
import tkinter.messagebox
from tkinter import *
import random
import pyperclip

# ---------------------------- SEARCH GENERATOR ------------------------------- #


def search():
    website = website_input.get()
    try:
        with open(file="data.json", mode="r") as file:
            data = json.load(file)
    except FileNotFoundError:
        tkinter.messagebox.showinfo(title="Error", message="No entries in Password Manager.")
    else:
        if website in data:
            username = data[website]["username"]
            password = data[website]["password"]
            tkinter.messagebox.showinfo(title=website, message=f"Username: {username}\nPassword: {password}")
        else:
            tkinter.messagebox.showinfo(title="Error", message="Website not found.")


# ---------------------------- PASSWORD GENERATOR ------------------------------- #


def generate():
    letters = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
    numbers = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
    symbols = ['!', '#', '$', '%', '&', '(', ')', '*', '+']

    nr_letters = random.randint(8, 10)
    nr_symbols = random.randint(2, 4)
    nr_numbers = random.randint(2, 4)

    password_letters = [random.choice(letters) for _ in range(nr_letters)]
    password_symbols = [random.choice(symbols) for _ in range(nr_symbols)]
    password_numbers = [random.choice(numbers) for _ in range(nr_numbers)]

    password_list = password_letters + password_symbols + password_numbers
    random.shuffle(password_list)
    password = "".join(password_list)
    password_input.delete(0, tkinter.END)
    password_input.insert(0, password)
    pyperclip.copy(text=password)

# ---------------------------- SAVE PASSWORD ------------------------------- #


def save_data():
    website = website_input.get()
    username = username_input.get()
    password = password_input.get()
    data = {
        website: {
            "username": username,
            "password": password
        }
    }

    if len(website) == 0 or len(username) == 0 or len(password) == 0:
        tkinter.messagebox.showinfo(title="Oops", message="Don't leave any fields empty!")
    else:
        try:
            with open("data.json", "r") as file:
                old_data = json.load(file)
        except FileNotFoundError:
            with open("data.json", "w") as file:
                json.dump(data, file, indent=4)
        else:
            old_data.update(data)
            with open("data.json", "w") as file:
                json.dump(old_data, file, indent=4)
        finally:
            website_input.delete(0, END)
            password_input.delete(0, END)
        tkinter.messagebox.showinfo(title="Done!", message="Login info added!")


# ---------------------------- UI SETUP ------------------------------- #


window = Tk()
window.title("Password Manager")
window.minsize(width=500, height=400)
window.config(padx=20, pady=20)

canvas = Canvas(width=200, height=200, highlightthickness=0)
lock_img = PhotoImage(file="logo.png")
canvas.create_image(100, 100, image=lock_img)
canvas.grid(column=1, row=0)

website_text = Label(text="Website:")
website_text.grid(column=0, row=1)
website_input = Entry(width=21)
website_input.grid(column=1, row=1)
search_button = Button(text="Search", command=search, width=13)
search_button.grid(column=2, row=1)

username_text = Label(text="Email/Username:")
username_text.grid(column=0, row=2)
username_input = Entry(width=35)
username_input.insert(0, "straska")
username_input.grid(column=1, row=2, columnspan=2)

password_text = Label(text="Password:")
password_text.grid(column=0, row=3)
password_input = Entry(width=21)
password_input.grid(column=1, row=3)
gen_password_button = Button(text="Generate Password", command=generate)
gen_password_button.grid(column=2, row=3)

add_button = Button(text="Add", width=35, command=save_data)
add_button.grid(column=1, row=4, columnspan=2)

window.mainloop()
