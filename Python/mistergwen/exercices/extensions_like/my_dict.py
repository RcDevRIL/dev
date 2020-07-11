class MyDict(dict):
    def __missing__(self, key):
        print('Clé manquante : ', key)
        self[key] = []
        return self[key]
