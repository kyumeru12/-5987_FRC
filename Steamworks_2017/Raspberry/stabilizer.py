import numpy as np


class Stabilizer:
    def __init__(self, n_last_measure):
        self.n_last_measure = n_last_measure
        self.measures = []

    def insert_measure(self, measure):
        self.measures.append(measure)
        del self.measures[:-5]

    def biggest(self):
        return max(self.measures)

    def smallest(self):
        return min(self.measures)

    def avg(self):
        return sum(self.measures)/len(self.measures)

    def median(self):
        return sorted(self.measures)[int(len(self.measures) / 2)]