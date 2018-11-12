#Importation des bibliothèques nécessaires

import pygame

from pygame.locals import *


#Initialisation de la bibliothèque Pygame

pygame.init()


#Création de la fenêtre

fenetre = pygame.display.set_mode((1280, 720), RESIZABLE)
#Variable qui continue la boucle si = 1, stoppe si = 0

continuer = 1
origin=(0,0)
x_perso = 0
y_perso = 0

#ajout d'un background

bG = pygame.image.load("resource/background.jpg").convert()
fenetre.blit(bG,origin)

#ajout d'un personnage

perso = pygame.image.load("resource/perso.png").convert_alpha()
pos_perso = perso.get_rect()
fenetre.blit(perso,origin)

pygame.display.flip()
#Boucle infinie

while continuer:
    for event in pygame.event.get():
        
        if event.type == QUIT:

            continuer = 0
            
        if event.type == KEYDOWN:

            if event.key == K_DOWN:

                pos_perso = pos_perso.move(0,-100)
                fenetre.blit(bG,origin)
                fenetre.blit(perso,pos_perso)
                pygame.display.flip()

            elif event.key == K_UP:

                pos_perso = pos_perso.move(0,100)
                fenetre.blit(bG,origin)
                fenetre.blit(perso,pos_perso)
                pygame.display.flip()

            elif event.key == K_RIGHT:

                pos_perso = pos_perso.move(100,0)
                fenetre.blit(bG,origin)
                fenetre.blit(perso,pos_perso)
                pygame.display.flip()

            elif event.key == K_LEFT:

                pos_perso = pos_perso.move(-100,0)
                fenetre.blit(bG,origin)
                fenetre.blit(perso,pos_perso)
                pygame.display.flip()

            elif event.key == K_RETURN:

                continuer = 0

        if event.type == MOUSEBUTTONUP:

            if event.button == 1:

                pos_perso[0] = event.pos[0]
                pos_perso[1] = event.pos[1]
            

        fenetre.blit(bG,origin)
        fenetre.blit(perso,pos_perso)
        pygame.display.flip()

fenetre = pygame.display.quit()
