var net = require('net');

// Notre client se connecte sur le serveur local
var SERVER_HOST = '127.0.0.1';
var SERVER_PORT = 3333;

// Création du "socket" de connexion TCP/IP
var client = new net.Socket();

// Notre objet "client" est une instance d'EventEmitter
// (voir https://nodejs.org/api/events.html)
// On peut donc lui attacher des fonctions,
// appelées "callback" ou "handler", afin de réagir
// à certains évènements liés au cycle de vie de l'objet

// On attache un callback à notre client pour
// l'évènement "connect". En passant une référence
// à la fonction "onConnect" on indique que l'on
// souhaite que celle ci soit automatiquement
// invoquée lorsque le client sera connecté.
client.on('connect', onConnect);

// Les données transmises par le serveur au client sont
// automatiquement transformées sous forme d'évènement
// par NodeJS. On peut récupérer ces données en ajoutant
// un callback sur notre objet "client" pour l'évènement
// "data"
client.on('data', onData);

// L'évènement "close" est émis lorsque la connexion
// avec le serveur est close.
client.on('close', onClose);

// L'évènement "error" est émis lorsque une erreur
// est soulevée. (exemple: perte de connexion)
client.on('error', onError);


// On initialise la connexion au serveur
console.log('Tentative de connexion au serveur: %s:%s', SERVER_HOST, SERVER_PORT);
client.connect(SERVER_PORT, SERVER_HOST);
var req='';

function onConnect() {

  // Nous sommes connecté au serveur !
  console.log('Connecté sur ' + SERVER_HOST + ':' + SERVER_PORT);

  // TODO envoyer les commandes au serveur ici

  // On peut envoyer des données au serveur via la méthode
  // write() du client
  // Exemple
  client.write('status;');
  client.write('add 10123;');
  client.write('sub 2;');
  client.write('mul 3;');
  client.write('div 5;');
  client.write('status;');
  client.write('reset;');
  
  // Si on le souhaite, on peut clore la connexion
  // au serveur via la méthode destroy()
  //client.destroy();

}

function onData(data) {

  // Les données sont reçues sous forme de bytes. On les transforme
  // en chaine de caractères UTF-8 pour faciliter leur manipulation
  var str = data.toString('utf8');
  console.log('Données reçues du serveur: %s', str);

}

function onClose() {
  console.log('La connexion au serveur a été close.')
}

function onError(err) {
  console.log('Une erreur a été soulevée: %s', err);
  process.exit(1);
}
