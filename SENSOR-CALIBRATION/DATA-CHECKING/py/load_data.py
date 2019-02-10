import indices as I
from datetime import datetime, timedelta
import consts as C
from ind import RAV, AV, PMS
from tqdm import tqdm
import os


class Load:
    @staticmethod
    def _grid(l):
        g = list()
        for i in range(l):
            g.append([])
        return g

    @staticmethod
    def _parse_name(obj):
        name = str(obj)
        name = name.split("'")
        name = name[1]
        return name

    @staticmethod
    def _format(row, sep=','):
        el = row[len(row) - 1]
        row = row.replace(el, "")
        row = row.split(sep)
        return row

    @staticmethod
    def _enc_gen(grid, obj):

        res = Load._grid(obj.LENGTH)
        for row in tqdm(grid):

            row = Load._format(row)
            if obj.LENGTH != len(row):
                continue

            # Encode every value of line and append
            for i in range(len(row)):
                val = eval(row[i])
                if i == obj.TIMESTAMP:
                    val = datetime.fromtimestamp(val)
                res[i].append(val)

        return res

    @staticmethod
    def _enc_av(grid, obj):
        res = Load._grid(AV.LENGTH)
        grid.remove(grid[0])

        for row in tqdm(grid):
            row = Load._format(row, ';')
            # Skip the corrupt value
            if len(row) != obj.LENGTH:
                continue

            # ENCODE: TIMESTAMP
            rts = row[obj.TIMESTAMP]
            fts = eval(rts) - 18000
            dts = datetime.fromtimestamp(fts)
            res[AV.TIMESTAMP].append(dts)

            # ENCODE: DATA
            res[AV.AQI].append(eval(row[RAV.AQI_US]))
            res[AV.PM25].append(eval(row[RAV.PM2_5]))
            res[AV.PM10].append(eval(row[RAV.PM10]))
            res[AV.TEMPERATURE].append(
                eval(row[RAV.TEMPERATURE_C]))
            res[AV.HUMIDITY].append(
                eval(row[RAV.HUMIDITY_RH]))
        return res

    @staticmethod
    def load(path, obj):

        print("Loading:>>", "------------------------")
        print("Loading:>>", "loading", Load._parse_name(obj), "Data")

        # Read all data from file
        # It is in row major format
        print("Loading:>>", "Reading file.")
        print("Loading:>>", "FILE-PATH =", path)
        data = open(path).readlines()
        print("Loading:>>", "Done reading.")

        enc = Load._enc_gen
        if obj == RAV:
            enc = Load._enc_av

        res = enc(data, obj)
        print("Loading:>>", "Done encoding")
        print("Loading:>>", "------------------------")
        return res


class Save:

    @staticmethod
    def _open_file(base, file_name):
        file_path = base + file_name
        if base != "":
            print("Creating Directory:", base)
            os.makedirs(base, exist_ok=True)
        print("Saving file to", file_path)
        file = open(file_path, "w+")
        return file

    @staticmethod
    def _get_line(data, r, obj):
        row = []
        # decode all Entries
        for c in range(obj.LENGTH):
            entry = data[c][r]
            if c == obj.TIMESTAMP:
                entry = entry.timestamp()
            entry = str(entry)
            row.append(entry)
        row = ','.join(row)
        row += '\n'
        return row

    @staticmethod
    def save(base, file_name, data, obj):
        file = Save._open_file(base, file_name)
        for r in tqdm(range(len(data[0]))):
            row = Save._get_line(data, r, obj)
            file.write(row)
        file.close()
