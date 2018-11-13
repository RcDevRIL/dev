#######IMPORTATION DES LIB REQUISES#####

import pygame
import sys
import time
from pygame.locals import *

########################################

#########VARIABLES GLOBALES#############

origin = (0,0)
screen = pygame.display.set_mode((690,590),RESIZABLE)
M = [[0, 0, 0, 0, 0, 0, 0], \
     [0, 0, 0, 0, 0, 0, 0], \
     [0, 0, 0, 0, 0, 0, 0], \
     [0, 0, 0, 0, 0, 0, 0], \
     [0, 0, 0, 0, 0, 0, 0], \
     [0, 0, 0, 0, 0, 0, 0]]
#me demandez pas comment j'ai trouver ces coordonnées...
#ça veut dire que si on change de background, faudra refaire ça

coord_M = [[(17,11), (114,11), (210,11), (307,11), (403,11), (500,11), (596,11)], \
     [(17,109), (114,109), (210,109), (307,109), (403,109), (500,109), (596,109)], \
     [(17,206), (114,206), (210,206), (307,206), (403,206), (500,206), (596,206)], \
     [(17,304), (114,304), (210,304), (307,304), (403,304), (500,304), (596,304)], \
     [(17,401), (114,401), (210,401), (307,401), (403,401), (500,401), (596,401)], \
     [(17,498), (114,498), (210,498), (307,498), (403,498), (500,498), (596,498)]]


joueur=1#joueur = 1 ou 2, pour choisir le bon pion et stocker les pions dans la matrice M
draw=1
i=0
nbTours=0
won=False
########################################

pygame.init()#lancement de pygame

######CHARGEMENT DES IMAGES DU JEU######

backGround=pygame.image.load("resource/Grille2.png").convert()
sizeBG = backGround.get_size()
yel = pygame.image.load("resource/PionJaune.png").convert_alpha() #convert_alpha() résoud la transparence
sizeYel = yel.get_size()
red = pygame.image.load("resource/PionRouge.png").convert_alpha()
sizeRed = red.get_size()

########################################

###########FONCTIONS################

def choix_col(x,y):
    return (int)((x-16)/97)
def choix_ligne(pawns,col):
    for i in range(5,-1,-1):
        if(not pawns[i][col]==0):
            continue
        else:
            break
    return i
def draw_pawns(pawns):
    for i in range(len(pawns)):
        for j in range(len(pawns[i])):
            if pawns[i][j]==1:
                screen.blit(yel,coord_M[i][j])
                pygame.display.flip()
            if pawns[i][j]==2:
                screen.blit(red,coord_M[i][j])
                pygame.display.flip()

def yel_or_red(nb):
    yel,red=1,2
    if(nb%2==0):
        return yel
    else:
        return red
####################################

#######AFFICHAGE "PLATEAU" DU JEU#######

screen = pygame.display.set_mode(sizeBG)
screen.blit(backGround,origin)
pygame.display.flip() #update TOUT le screen

while (nbTours<42 and not won):

    draw_pawns(M)
    
    
    for event in pygame.event.get():

        if event.type == QUIT:
            won = True
            
        if event.type == MOUSEBUTTONUP:
            if event.button == 1:#clic gauche
                x,y = pygame.mouse.get_pos()
                col = choix_col(x,y)
                joueur = yel_or_red(nbTours)
                ligne = choix_ligne(M,col)
                M[ligne][col] = joueur
                nbTours = nbTours+1
                
                
screen = pygame.display.quit()
