# This program calculates the intrinsic value of a stock

import tkinter

window = tkinter.Tk()
window.title("Intrinsic Value Calculator")
window.config(bg="green", padx=20, pady=20)
window.minsize(width=600, height=500)

label = tkinter.Label(text="Graham's Intrinsic Value Calculator", font=("Courier", 26))
label.place(x=0, y=10)

# EPS
eps_label = tkinter.Label(text="Trailing 12-month EPS:", font=("Courier", 20))
eps_label.place(x=10, y=80)
eps_entry = tkinter.Entry(width=10)
eps_entry.place(x=80, y=130)

# G
g_label = tkinter.Label(text="Growth Rate of next 7-10 years:", font=("Courier", 20))
g_label.place(x=10, y=180)
g_entry = tkinter.Entry(width=10)
g_entry.place(x=80, y=230)

# Y
y_label = tkinter.Label(text="AA Corp Bond Rate:", font=("Courier", 20))
y_label.place(x=10, y=280)
y_entry = tkinter.Entry(width=10)
y_entry.place(x=80, y=330)

intrinsic_value_label = tkinter.Label(text="Intrinsic Value: ", font=("Courier", 20))
intrinsic_value_label.place(x=100, y=400)


def calculate():
    eps = eps_entry.get()
    g = g_entry.get()
    y = y_entry.get()

    if eps != "" and g != "" and y != "":
        value = (float(eps) * (8.5 + 2 * float(g)) * 4.4) / float(y)
        intrinsic_value_label.config(text="Intrinsic Value: " + str(value))
    else:
        intrinsic_value_label.config(text="Intrinsic Value: Error")


button = tkinter.Button(text="Calculate", command=calculate)
button.place(x=10, y=400)


window.mainloop()