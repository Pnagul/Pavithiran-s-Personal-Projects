# Created by: Tyler Baboolal, Yusuf Hamed, Pavithiran Naguleswaran, Aelmar
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns 
import numpy;
import math;

def programCount(data,labels):

    #Create counters for number of responses for each program
    stem = 0
    art = 0
    communications = 0
    other = 0
    business = 0
    
    #Grab program cololum of data and store into program
    program = data.iloc[:,1:2].values


    #loop through program that returns a list of these values and collect the respones 
    for x in program:
        if x == labels[0]:
            stem+= 1
        elif x == labels[1]:
            art+= 1
        elif x == labels[2]:
            business +=1 
        elif x == labels[3]:
            communications += 1
        else:
            other += 1

    #set this to a list and return 
    program_dist = [stem,art,business,communications,other]
    #return list 
    return program_dist
        
def ageCount (data):
    #grab the age data col of the data sheet 
    age = data["What's your age"]
    #create an empty array with three values for each age group
    ages = [0,0,0]


    #loop through the data and update the array with the data 
    for x in age:
        if x == "18-19":
            ages[0] += 1
        elif x == "20-23":
            ages[1] += 1
        else:
            ages[2] += 1
    
    return ages

def agePlag(data):

    #grab the col of the respones for the question what's your age 
    age = data["What's your age"]
    
    #grab the col of data for the question of asking the respondents for the plagerism question
    work = data["Do you believe using ChatGPT for writing is plagiarism? "]
    
    #create a 2d area one for yes responses in the age group and another array for the no respones in the age groups 
    workPerAge = [[0,0,0],[0,0,0]]


    #loop through the data and add to the arraylist as accordingly 
    for x in range (len(age)):
        if age[x] == "18-19":
            if work[x] == 1:
                workPerAge[0][0] += 1
            else:
                workPerAge[1][0] += 1
        elif age[x] == "20-23":
            if work[x] == 1:
                workPerAge[0][1] +=1
            else:
                workPerAge[1][1] +=1
        else:
            if work[x] == 1:
                workPerAge[0][2] +=1
            else:
                workPerAge[1][2] +=1

    #print(workPerAge)
    return workPerAge

def theBehemoth(data):
    #grab the data from col of asking the respondents what program they are from and store in a list 
    program = data.iloc[:,1:2].values
    #grab the col of age data for the age groups and store in a list 
    age = data["What's your age"]
    #grab the col of asking the respondets if they use chatgpt for completting work assigned 
    use =  data["Do you use ChatGPT to aid you in completing work assigned"]

    #create a 2d array which collcets the data of which majors and agegroups use chatgpt to complete school work and others that does not 
    graph = [[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0],[0,0,0]]

    #loop through all the data and aadd to it accordingly 
    for x in range (len(program)):
        if program[x] == "STEM":
            if age[x] == "18-19":
                if use[x] == 1:
                    graph[0][0] +=1
                else:
                    graph[1][0] +=1

            elif age[x] == "20-23":
                if use[x] == 1:
                    graph[0][1] +=1
                else:
                    graph[1][1] +=1
            else:
                if use[x] == 1:
                    graph[0][2] +=1
                else:
                    graph[1][2] +=1

        elif program[x] == "Art":
            if age[x] == "18-19":
                if use[x] == 1:
                    graph[2][0] +=1
                else:
                    graph[3][0] +=1

            elif age[x] == "20-23":
                if use[x] == 1:
                    graph[2][1] +=1
                else:
                    graph[3][1] +=1
            else:
                if use[x] == 1:
                    graph[2][2] +=1
                else:
                    graph[3][2] +=1
        elif program[x] == "Business":
            if age[x] == "18-19":
                if use[x] == 1:
                    graph[4][0] +=1
                else:
                    graph[5][0] +=1

            elif age[x] == "20-23":
                if use[x] == 1:
                    graph[4][1] +=1
                else:
                    graph[5][1] +=1
            else:
                if use[x] == 1:
                    graph[4][2] +=1
                else:
                    graph[5][2] +=1
            
        elif program[x] == "Communications":
            if age[x] == "18-19":
                if use[x] == 1:
                    graph[6][0] +=1
                else:
                    graph[7][0] +=1

            elif age[x] == "20-23":
                if use[x] == 1:
                    graph[6][1] +=1
                else:
                    graph[7][1] +=1
            else:
                if use[x] == 1:
                    graph[6][2] +=1
                else:
                    graph[7][2] +=1
        elif program[x] == "Other":
            if age[x] == "18-19":
                if use[x] == 1:
                    graph[8][0] +=1
                else:
                    graph[9][0] +=1

            elif age[x] == "20-23":
                if use[x] == 1:
                    graph[8][1] +=1
                else:
                    graph[9][1] +=1
            else:
                if use[x] == 1:
                    graph[8][2] +=1
                else:
                    graph[9][2] +=1
    

    #return the graph 
    return graph
def workUseGPT(data):
    #get data from this question and store into list 
    numhrofwork = data["Number of hours spent studying a week?"]

    #get data from this question and store into list
    oftenusegpt = data["If yes, how often to do you use ChatGPT?"]

    #get the number hrs of work for and the usefulness students find and store into the thing 
    array = [[0,0,0],[0,0,0],[0,0,0]]

    #loop through and add to array accrodingly 
    for x in range (len(numhrofwork)):
        
        if numhrofwork[x] == "1-3" or numhrofwork[x]==0:
            if oftenusegpt[x] <=2:
                array [0][0] += 1
            elif oftenusegpt[x]<=4:
                array[1][0] += 1
            else:
                array[2][0] += 1

        elif numhrofwork[x] == "4-7":
            if oftenusegpt[x] <=2:
                array [0][1] += 1
            elif oftenusegpt[x]<=4:
                array[1][1] += 1
            else:
                array[2][1] += 1
        else:
            if oftenusegpt[x] <=2:
                array [0][2] += 1
            elif oftenusegpt[x]<=4:
                array[1][2] += 1
            else:
                array[2][2] += 1
                
    return array


def ageUseChatGPT(data):

    #same idea as the method but with age groups instead 
    ageusechatgpt = data["What's your age"]

    oftenusegpt = data["If yes, how often to do you use ChatGPT?"]

    array = [[0,0,0],[0,0,0],[0,0,0]]
    for x in range (len(ageusechatgpt)):
        
        if ageusechatgpt[x] == "18-19":
            if oftenusegpt[x] <=2:
                array [0][0] += 1
            elif oftenusegpt[x]<=4:
                array[1][0] += 1
            else:
                array[2][0] += 1

        elif ageusechatgpt[x] == "20-23":
            if oftenusegpt[x] <=2:
                array [0][1] += 1
            elif oftenusegpt[x]<=4:
                array[1][1] += 1
            else:
                array[2][1] += 1
        else:
            if oftenusegpt[x] <=2:
                array [0][2] += 1
            elif oftenusegpt[x]<=4:
                array[1][2] += 1
            else:
                array[2][2] += 1
                
    return array

if __name__ == "__main__":
    #create labels for each major and age groups 
    labels = ["STEM","Art","Business","Communications","Other"]
    labelA = ["18-19","20-23","24+"]
    labelW = ["0-3","4-7","7+"]

    #read the data from the excel sheet and store it into data 
    data = pd.read_excel("ChatGPT Ethics Survey (Responses).xlsx")
    
    #Skip first row 
    data = data.iloc[:,1:]

    
    #map the data of Yes or no for these coloums to just 1 and 0 
    data["Do you have a job/extra curricular activity outside of school?"] = data["Do you have a job/extra curricular activity outside of school?"].map({"Yes": 1, "No": 0})
    data["Do you believe using ChatGPT for writing is plagiarism? "] = data["Do you believe using ChatGPT for writing is plagiarism? "].map({"Yes": 1, "No": 0})
    data["Do you use ChatGPT to aid you in completing work assigned"] = data["Do you use ChatGPT to aid you in completing work assigned"].map({"Yes": 1, "No": 0})


    
    #create a 2 by 2 subplot (so it holds 4 grpahs ) and scale it upwards 
    fig, ax = plt.subplots(2,2,figsize = (12,12))

    #print(data)
    
    #set width value to 0.4 to change the bar graphs width
    width = 0.4
    
    #create the pie graph with the data from above 
    ax[0,0].pie(programCount(data,labels),labels = labels,autopct ='%1.1f%%')

    #set the title 
    ax[0,0].title.set_text("Program Distribution")
    

    #create a bargraph and the age distroubution setting the x axis to age groups and y to number of students 
    ax[0,1].bar(range(len(labelA)),ageCount(data),edgecolor = "Black")
    ax[0,1].title.set_text("Age Distribution")
    ax[0,1].set_xlabel("Age Groups")
    ax[0,1].set_ylabel("Number of students")


   

    #set the x labels to the names of the age groups 
    ax[0,1].set_xticks(range(len(labelA)),labelA)
    

    #create a value integer using numpy this is to use for the xticks spacing when changing the width of the bar graphs 
    values = numpy.arange(len(labelA))

    #create the bar graph 
    ax[1,0].bar(values,agePlag(data)[0],width,label="Yes",edgecolor = "Black")
    
    #set title 
    ax[1,0].title.set_text("Age groups that believe using ChatGPT is plagerism")

    #set x label
    ax[1,0].set_xlabel("Age groups")

    #set y label
    ax[1,0].set_ylabel("Number of responses")

    #display legend label when creating the graph
    ax[1,0].legend()

    #set the spacing and labels for the bar graph 
    ax[1,0].set_xticks(values+0.2,labelA)

    #create a another bargraph and map it onto the same plot as the previous one to make a double bar graph 
    ax[1,0].bar(values+width,agePlag(data)[1],width,label="No",edgecolor = "Black")

    #display the legend 
    ax[1,0].legend()
    #plt.xlabel("Program")
    #plt.ylabel("Age")
    

    #store the list that the method returns to a 
    a = theBehemoth(data)

    #create a yes list to filter through the yes responses from the list 
    yes = []
    for i in range(0,len(a),2):
        yes = yes +[a[i]]



    #create a list for age groups and put it into the list 
    ageGroup1 = []
    for i in range(len(yes)):
        ageGroup1.append(yes[i][0])
    
    ageGroup2 = []
    for i in range(len(yes)):
        ageGroup2.append(yes[i][1])
    
    
    ageGroup3 = []
    for i in range(len(yes)):
        ageGroup3.append(yes[i][2])
    

    #create and list to make starting points for the 3rd stack bar in the graph
    start = []
    for i in range(len(ageGroup1)):
        start.append(ageGroup1[i]+ageGroup2[i])
    

    #create this to use with the new widths
    vals = numpy.arange(len(labels))


    #make bargraphs on the same plot and stack them ontop of eachother to make a stacking bar graph   
    ax[1,1].bar(vals,ageGroup1,width,label = "18-19 that use ChatGPT",edgecolor = "Black")
   
    ax[1,1].bar(vals,ageGroup2,width,bottom=ageGroup1,label = "20-23 that use ChatGPT",edgecolor = "Black")
    
    ax[1,1].bar(vals,ageGroup3,width,bottom=start,label="24+ that use ChatGPT",edgecolor = "Black")
    
    #set the labs 
    ax[1,1].set_xticks(vals+0.2,labels)


    #get the no half of the responeses 
    no = []

    for i in range (1,len(a),2):
        no = no + [a[i]]

    #create a list for age groups and put it into the list 
    ageGroup1 = []
    for i in range(len(no)):
        ageGroup1.append(no[i][0])
    
    ageGroup2 = []
    for i in range(len(no)):
        ageGroup2.append(no[i][1])

    
    ageGroup3 = []
    for i in range(len(no)):
        ageGroup3.append(no[i][2])
    

    #start position for the third stacked bar graph
    start = []
    for i in range(len(ageGroup1)):
        start.append(ageGroup1[i]+ageGroup2[i])
    

    #make bargraphs on the same plot and stack them ontop of eachother to make a stacking bar graph  
    ax[1,1].bar(vals+width,ageGroup1,width,label = "18-19 that don't use ChatGPT",edgecolor = "Black")
    
    ax[1,1].bar(vals+width,ageGroup2,width,bottom=ageGroup1,label = "20-23 that don't use ChatGPT",edgecolor = "Black")
    
    ax[1,1].bar(vals+width,ageGroup3,width,bottom=start,label="24+ that don't use ChatGPT",edgecolor = "Black")
    

    #reordering how the legends appear on the graph 
    handels, labels = ax[1,1].get_legend_handles_labels()

    order = [0,3,1,4,2,5]

    #printing the legends in the reoder 
    ax[1,1].legend([handels[idx] for idx in order],[labels[idx] for idx in order])
    
    #set labels of the graph AND title
    ax[1,1].set_xlabel("Majors")
    ax[1,1].set_ylabel("Number of responses")
    ax[1,1].title.set_text("Majors and age groups that agree and disagree with the use of ChatGPT")

    #plot
    plt.show()
    plt.figure(2)
    width = 0.2

    #store method call into this variable 
    oftenWorkGPT = workUseGPT(data)


    values = numpy.arange(len(labelW))




    #plotting the bar graph for the number hours of studying and usefulness 
    plt.title("How often students work a week vs how useful students find ChatGPT")
    plt.bar(values,oftenWorkGPT[0],width,label = "Students that find chatgpt somewhat useful",edgecolor = "Black")
    plt.legend()
    plt.bar(values+width,oftenWorkGPT[1],width,label = "Students that find chatgpt useful",edgecolor = "Black")
    plt.legend()
    plt.bar(values+width+width,oftenWorkGPT[2],width,label = "Students that find chatgpt very useful",edgecolor = "Black")
    plt.legend()
    plt.xlabel("Hours of work done a week")
    plt.ylabel("Number of responses")
    plt.xticks(values+width,labelW)
    #plt.yticks(range(0, 19))




    plt.figure(3)
    
    ageusechatgpt = ageUseChatGPT(data)


    values = numpy.arange(len(labelA))




    #same idea as the previous plot above
    plt.title("Age groups of students and how useful students find ChatGPT")
    plt.bar(values,ageusechatgpt[0],width,label = "Students that find chatgpt somewhat useful",edgecolor = "Black")
    plt.legend()
    plt.bar(values+width,ageusechatgpt[1],width,label = "Students that find chatgpt useful",edgecolor = "Black")
    plt.legend()
    plt.bar(values+width+width,ageusechatgpt[2],width,label = "Students that find chatgpt very useful",edgecolor = "Black")
    plt.legend()
    plt.xlabel("Age groups")
    plt.ylabel("Number of responses")
    plt.xticks(values+width,labelA)
    #plt.yticks(range(0, 19))
    plt.show()





    #print(a)
    