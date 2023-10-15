# Pavithiran Naguleswaran
# ---------------------------------------------------------------------------------------
# Project allows the user to generate a unique hexadecimal RGB code for their chosen colour.
# User can use the 3 sliders/scales to customize their colour.

#import tkinter module
import tkinter

# function that creates a rgb code & and displays a visualization of the code 
# whenever the value of the sliders change
def generate_rgb_code(position):
    # get the int value from each slider
    red = red_sld.get()
    green = green_sld.get()
    blue = blue_sld.get()

    # Combine the int values of each slider into a tuple
    # Convert the tuple of RGB colour values to tkinter colour code
    rgb_code = "#%02x%02x%02x" % (red,green,blue)

    # chnage backgorund colour to the user's custom colour using the RGB code
    cstm_colour.config(bg=rgb_code)

    # clear the text of the label & set the rgb code as the text of the label
    code_display["text"] = rgb_code



if __name__ == "__main__":
    #create and assign a new window to the variable 'window'
    window = tkinter.Tk()

    #Create a title for the window
    window.title("Customize Your Own Colour")

    # set window size
    window.geometry("700x180")

    # Create a frame for the sliders to be organized in
    sld_frame = tkinter.Frame(master=window, relief = tkinter.GROOVE, borderwidth= 5)

    # pack frame to the left of the window
    sld_frame.pack(side = "left", fill = tkinter.BOTH)


    #Label that shows the user's custom colour
    cstm_colour = tkinter.Label(master = window, bg="black", width=300, height=180)
    cstm_colour.pack(fill =tkinter.BOTH)

    # list of color labels
    colour_names = ["Red", "Green", "Blue"]

    # for loop to iterate over the list of colour labels to create 3 different labels
    for idx, label in enumerate(colour_names):
        # Create a label widget with the text from the colours list
        # Change foreground colour to the corresponding colour in the list
        clr_label = tkinter.Label(master=sld_frame, text = label, fg = label, relief= tkinter.SUNKEN)
        # Pack each label neatly to the 'bottom-left' in its grid space
        clr_label.grid(row = idx, column = 0, sticky= "se")


    # create sliders for each colour; Red, Blue, & Green
    # Pack sliders beside their corresponding labels

    # Red slider
    red_sld = tkinter.Scale(master = sld_frame, from_=0, to = 255, length =300, fg = "red", orient="horizontal", command = generate_rgb_code)
    red_sld.grid(row=0, column=1)

    # Green slider
    green_sld = tkinter.Scale(master = sld_frame, from_=0, to = 255, length =300, fg = "green", orient="horizontal", command = generate_rgb_code)
    green_sld.grid(row=1, column=1)

    # Blue slider
    blue_sld = tkinter.Scale(master = sld_frame, from_=0, to = 255, length =300, fg = "blue", orient="horizontal", command = generate_rgb_code)
    blue_sld.grid(row=2, column=1)

    # Create label & entry widgets to display RGB code to user
    code_label = tkinter.Label(master = sld_frame, text = "RGB code: ", relief= tkinter.SUNKEN)
    code_label.grid(row = 3, column=0, pady=15, sticky = "e", padx = 5)

    code_display = tkinter.Label(master= sld_frame, width = 50, relief= tkinter.SUNKEN)
    code_display.grid(row = 3, column = 1, pady= 15, padx = 5)


    window.mainloop()



