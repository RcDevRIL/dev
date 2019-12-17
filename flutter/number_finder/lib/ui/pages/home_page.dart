import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart';
import 'package:number_finder/data/models/user_repo.dart';
import 'package:number_finder/data/services/game_api.dart';
import 'package:number_finder/ui/pages/try_form.dart';
import 'package:provider/provider.dart';

class HomePage extends StatefulWidget {
  static const String routeName = '/';
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  static const String _title = 'NumberFinder';
  @override
  Widget build(BuildContext context) {
    Response response;
    String nick = Provider.of<UserRepo>(context).getNickname;
    return Scaffold(
      appBar: AppBar(
        title: const Text(_title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text('Hello $nick'),
            RaisedButton(
              onPressed: () async {
                response = await Provider.of<GameAPI>(context)
                    .startGame(Provider.of<UserRepo>(context).getNickname);
                if (response.statusCode == 200) {
                  var decoded = jsonDecode(response.body);
                  Provider.of<UserRepo>(context).token = decoded.token;

                  Navigator.of(context).pushNamed(TryForm.routeName);
                } else {
                  showDialog(
                    context: context,
                    builder: (context) => AlertDialog(
                      title: Text(
                        'Erreur',
                        style: TextStyle(
                          fontWeight: FontWeight.w700,
                        ),
                      ),
                      content: Text('Erreur avec l\'API...\n' +
                          'Response code: ${response.statusCode}\n' +
                          'Response body: ${response.body}'),
                      actions: <Widget>[
                        RaisedButton(
                          onPressed: () => Navigator.of(context).pop(),
                          color: Colors.blue,
                          child: Text(
                            'OK',
                            style: TextStyle(
                              color: Colors.white,
                            ),
                          ),
                        )
                      ],
                    ),
                  );
                }
              },
              child: Text('Get Token'),
            ),
          ],
        ),
      ),
    );
  }
}
