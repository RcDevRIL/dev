import json

print('\n###### CHARGEMENT JSON ######\n')
print('## Depuis un fichier...')
with open('data/data_json.json', 'r') as fp:
    my_data = json.load(fp)
# Ecrit le json dans la console. L'option indent permet d'avoir un output 'prettyfied'
print(f'Ce JSON contient:\n{json.dumps(my_data, indent=2)}')

print('## Depuis le code...')
fp = '{"local":{"leases":[{"mac":"02:00:c0:a8:00:66","name":"pcxubuntu","address":"192.168.0.200"},{"mac":"02:00:c0:a8:00:67","name":"pcxubuntu","address":"192.168.0.201"},{"mac":"02:00:c0:a8:00:68","name":"pcxubuntu","address":"192.168.0.202"}]}}'
my_data = json.loads(fp)
# Ecrit le json dans la console. L'option indent permet d'avoir un output 'prettyfied'
print(f'Ce JSON contient:\n{json.dumps(my_data, indent=2)}')

print('\n###### TRAITEMENT JSON ######\n')
print('## Sélection de champs...')
with open('data/data_json.json', 'r') as fp:
    my_data = json.load(fp)
# Utilisation d'une compréhension de liste
ips = [lease['address'] for lease in my_data['local']['leases']]
print(f'IPs = \n{json.dumps(ips, indent=2)}')


class Addresses:
    def __init__(self, address, mask, dynamic_ranges):
        self.address = address
        self.mask = mask
        self.dynamic_ranges = dynamic_ranges

    def __str__(self):
        return f"""
[
  {{
    ip : {self.address},
    mask : {self.mask},
    dynamic_ranges : {self.dynamic_ranges}
  }}
]
        """


print('## Aggrégation de données par redondance')
with open('data/addresses.json', 'r') as fp:
    addresses = json.load(fp)
# Look for IPs and remove redundant ones thanks to `dict.fromKeys`
ips_to_watch = list(dict.fromkeys([elt['address'] for elt in addresses]))
print(f'ips_to_watch = {ips_to_watch}')
aggreg = []
for ip in ips_to_watch:
    aggreg.append(Addresses(ip, '', []))
for elt in addresses:
    for address in aggreg:
        if(elt['address'] == address.address):
            address.mask = elt['mask']
            # Ici on ne doit pas utiliser '=' pour éviter d'écraser les données
            address.dynamic_ranges.extend(elt['dynamicRanges'])
for address in aggreg:
    print(f'Address {aggreg.index(address) + 1}:\n{address}')
