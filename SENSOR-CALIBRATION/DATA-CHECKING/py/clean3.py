F1 = '../../DATA+RESULTS/SPLIT-DEC-24/s1.csv'
F2 = '../../DATA+RESULTS/SPLIT-DEC-24/AV.csv'


def compare(r1, r2):
    if int(r1[5]) < 0:
        return False

    return True


def main():
    data1 = open(F1).readlines()
    data2 = open(F2).readlines()

    w1 = open(F1, 'w')
    w2 = open(F2, 'w')

    for i in range(len(data1)):
        r1 = data1[i]
        r2 = data2[i]
        if compare(r1.split(','), r2.split(',')):
            w1.write(r1)
            w2.write(r2)


main()
