# -*- coding: utf8 -*-
# Echo server program
import socket
from threading import Thread
import sys

HOST = ''               # Symbolic name meaning all available interfaces
PORT = 3333             # Arbitrary non-privileged port
step=0                  # Allow us to avoid wrong use of protocol (eg. client send two 'hey' in a row)
dico={b'hey':b'yeh',b'cmd':b'res',b'prc':b'crp',b'pay':b'yap',b'bye':b'eyb'}




def clientthread(conn):
    buffer=""
    while True:
        for value in dico.values():
            data=conn.recv(3)
            if not data: break
            data.decode("utf-8")
            print(data)
            conn.send(dico[data])
#       data = conn.recv(3)
#       if not data: break
#       data.decode("utf-8")
#       print(data)
#       if data==b'hey'and step==0:
#           conn.send(b'yeh')
#           step+=1
#       elif data==b'cmd' and step==1:
#           conn.send(b'res')
#           step+=1
#       elif data==b'prc' and step==2:
#           conn.send(b'crp')
#           step+=1
#       elif data==b'pay' and step==3:
#           conn.send(b'yap')
#           step+=1
#       elif data==b'bye' and step==4:
#           conn.send(b'eyb')
#           step=0
#           break
#       else:
    #      print("Invalid use of protocol!")
#          conn.send(b'eyb')
#           step=0
#           break
    conn.close()

def main():
    try:
        socket_number=2 #accept up to 25 connections
        sock_list=[]
        for i in range(socket_number):
            s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            s.setsockopt(socket.SOL_SOCKET,socket.SO_REUSEADDR,1)
            s.bind((HOST,PORT+1))
            s.listen(10)
            sock_list.append(s)
            print("Listening on %s %d" %(HOST,(PORT+i)))
            #print(sock_list)
        while 1:
            for j in range(len(sock_list)):
                try:
                    conn, addr =  sock_list[j].accept()
                except InterruptedError as msg:
                    print(str(msg))
                print(repr(conn))
                with conn:
                    print('Connected by', addr)
                    t=Thread(target = clientthread,args=conn)
                    t.start()
                #start_new_thread(clientthread,(conn,))
        s.close()
    except KeyboardInterrupt as msg:
        print(str(msg))
        sys.exit(0)

if __name__=="__main__":
    main()
                



#with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
#   try:
#        s.bind((HOST, PORT))
#   except socket.error as msg:
#       print(str(msg))
#   s.listen(10)
#   print("Listening on port :", PORT)
#   conn, addr = s.accept()
#   with conn:
#       print('Connected by', addr)
#       while True:
#           data = conn.recv(3)
#           if not data: break
#           data.decode("utf-8")
#           print(data)
#           if data==b'hey'and step==0:
#               conn.send(b'yeh')
#               step+=1
#           elif data==b'cmd' and step==1:
#               conn.send(b'res')
#               step+=1
#           elif data==b'prc' and step==2:
#               conn.send(b'crp')
#               step+=1
#           elif data==b'pay' and step==3:
#               conn.send(b'yap')
#               step+=1
#           elif data==b'bye' and step==4:
#               conn.send(b'eyb')
#               step=0
#               break
#           else:
#               print("Invalid use of protocol!")
#               conn.send(b'eyb')
#               step=0
#               break
#           
#           
#   s.close()
