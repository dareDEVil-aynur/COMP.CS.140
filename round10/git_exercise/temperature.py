def main():
    try:
        direction = input("Select transform direction (CF) or (FC): ").upper()
        if direction not in ['CF', 'FC']:
            print("Invalid direction. Please enter 'CF' or 'FC'.")
            exit()
        input_temp = float(input("Input temperature: "))
    except ValueError:
        print("Not valid input")
        exit()

    if direction == 'CF':
        celsius = t_transform(input_temp, 'c')
        fahrenheit = t_transform(input_temp, 'f')
        print("{} C = {} F".format(input_temp, fahrenheit))
    elif direction == 'FC':
        fahrenheit = t_transform(input_temp, 'f')
        celsius = t_transform(input_temp, 'c')
        print("{} F = {} C".format(input_temp, celsius))

def t_transform(temp, type):
    if type == 'c':
        return (temp - 32) * 5/9
    elif type == 'f':
        return temp * 9/5 + 32
    return None

if __name__ == "__main__":
    main()
