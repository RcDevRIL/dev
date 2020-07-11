from pathlib import Path

print('###### DOSSIER COURANT ######\n')
# Récupère le dossier courant
current_dir = Path.cwd()
# Affiche le nom court
print(f'Le nom du dossier courant est: {current_dir.name}')
# Affiche le nom long
print(f'Le chemin du dossier courant est: {current_dir}')

print('\n###### ECRITURE FICHIER ######\n')
# Récupère le répertoire 'home' de l'utilisateur et écrit 'debug = True' dans le fichier 'config.yaml'
yaml = Path(Path.home() / 'config.yaml')
yaml.write_text('debug = True')
# Lit le contenu du fichier
print(f'Le fichier {yaml.name} a ce contenu:\n{yaml.read_text()}')

print('\n###### LISTE DES FICHIERS ######\n')
cwd = Path.cwd()
print('{}:\n'.format(cwd))
for f in cwd.iterdir():
    if f.is_file():
        print(f'- {f.name} ({f.stat().st_size} bytes)')

# Affiche [WindowsPath('...'), WindowsPath('...'),...]
# print([f for f in cwd.iterdir() if f.is_file()])
