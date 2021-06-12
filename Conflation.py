import random
def conflation(px, py):
    return (px * py) / ((px * py) + (1 - px) * (1 - py))

p1 = 0.7
p2 = 0.3
pa = 0.73
pb = 0.27

# p11 = conflation(p1,pa)
# p22 = conflation(p2,pb)
# print(p11)
# print(p22)
# print(p11 + p22)

for i in range(10):
    #p1 = round(random.uniform(0.1,0.4),2)
    #p2 = round(0.4 - p1,2)
    p11 = round(conflation(p1,pa),2)
    p22 = round(conflation(p2,pb),2)
    p111 = round((p11/(p11+p22)) * (p1 + p2),2)
    p222 = round((p22 / (p11 + p22)) * (p1 + p2),2)
    print("p1:"+str(p1)+" p111:"+str(p111)+" p2:"+str(p2)+" p222:"+str(p222)+" > "+str(p111 + p222))
