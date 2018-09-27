var net = require('net');

var HOST = '127.0.0.1'; // On écoute sur toutes les interfaces
var PORT = 3333;

var server = net.createServer();

server.on('connection', onClientConnection);
server.on('listening', onListening);
server.on('error', onError);

server.listen(PORT, HOST);

var req=[];
var reqs=[];
var acc=0;

function onClientConnection(client) {

  console.log('Nouveau client depuis %s:%s', client.remoteAddress, client.remotePort);

  // On souhaite commencer à récupérer les données
  // provenant de ce nouveau client
  client.on('data', onClientData);
  client.on('close', onClientClose);

  function onClientData(data) {

    // Les données sont reçues sous forme de bytes. On les transforme
    // en chaine de caractères UTF-8 pour faciliter leur manipulation
    var str = data.toString('utf8');

    console.log('Données reçues de %s:%s: "%s"', client.remoteAddress, client.remotePort, str);

    // TODO désérialiser les données reçues et traiter les opérations.
	reqs=str.split(';',100);
	for(var i=0;i<reqs.length;i++){
		req=reqs[i].split(' ',2);
		console.log(req);
		switch(req[0]){
		case 'add':
			acc=add(parseFloat(req[1]));
			console.log("acc+%s = %s",req[1],acc);
			client.write(acc.toString()+';');
			break;
		case 'sub':
			acc=sub(parseFloat(req[1]));
			console.log("acc-%s = %s",req[1],acc);
			client.write(acc.toString()+';');
			break;
		case 'div':
			acc=div(parseFloat(req[1]));
			console.log("acc/%s = %s",req[1],acc);
			client.write(acc.toString()+';');
			break;
		case 'mul':
			acc=mul(parseFloat(req[1]));
			console.log("acc*%s = %s",req[1],acc);
			client.write(acc.toString()+';');
			break;
		case 'reset':
			acc=reset();
			console.log("acc = %s",acc);
			client.write(acc.toString()+';');
			break;
		case 'status':
			client.write(status().toString()+';');
			break;
		default:
			console.log("Request Error");
		}
	}
	// req=reqs.split(' ',2);
	// console.log(req);
	// switch(req[0]){
		// case 'add':
			// acc=add(parseFloat(req[1]));
			// console.log("acc+%s = %s",req[1],acc);
			// client.write(acc.toString());
			// req=[];
			// break;
		// case 'status':
			// client.write(acc.toString());
		// default:
			// console.log("Pas encore implementey");
	// }

  }

  function onClientClose() {
    console.log('Le client %s:%s s\'est déconnecté.', client.remoteAddress, client.remotePort);
  }

}

function onListening() {
  console.log("Serveur en écoute sur %s:%s", HOST, PORT)
}

function onError(err) {
  console.log('Une erreur a été soulevée: %s', err);
  process.exit(1);
}

function add(nb){
	return acc+nb;
}

function sub(nb){
	return acc-nb;
}

function div(nb){
	return acc/nb;
}

function mul(nb){
	return acc*nb;
}

function status(){
	return acc;
}

function reset(){
	return 0;
}


