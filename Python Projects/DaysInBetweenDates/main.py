# This program calculates the days in between to given date and the current date.

from datetime import datetime

days_in_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]


def days_since_0000(year, month, day):
    # Add days in month
    total_days = day

    # Adds the months passed by
    for m in range(0, month-1):
        total_days += days_in_month[m]

    # Adds years passed by
    total_days += year * 365

    # Adds leap year days
    for y in range(0, year):
        if ((y % 4 == 0) and (y % 100 != 0)) or (y % 400 == 0):
            total_days += 1

    # Adds a day if current year has a leap day
    if month > 2 and ((year % 4 == 0) and (year % 100 != 0)) or (year % 400 == 0):
        total_days += 1
    return total_days


# Get input date
input_date = input("Enter a date (MM-DD-YYYY): ").split("-")
input_year = int(input_date[2])
input_month = int(input_date[0])
input_day = int(input_date[1])

# Get today's date
today = datetime.today()
today_year = today.year
today_month = today.month
today_day = today.day

# Prints day count to user
print(str(abs(days_since_0000(input_year, input_month, input_day) - days_since_0000(today_year, today_month, today_day))) + " days in between.")
