import 'package:flutter/material.dart';

import 'package:number_finder/data/models/user_repo.dart';
import 'package:number_finder/data/services/game_api.dart';
import 'package:number_finder/ui/pages/pages.dart';
import 'package:provider/provider.dart';

void main() => runApp(NumberFinder());

/// This Widget is the main application widget.
class NumberFinder extends StatelessWidget {
  static const String _title = 'NumberFinder';

  @override
  Widget build(BuildContext context) {
    return MultiProvider(
      providers: [
        ChangeNotifierProvider(
          create: (context) => UserRepo(nickname: 'Romain'),
        ),
        ChangeNotifierProvider(
          create: (context) => GameAPI(),
        ),
      ],
      child: MaterialApp(
        debugShowCheckedModeBanner: false,
        theme: ThemeData(
            appBarTheme: AppBarTheme(
          color: Colors.blueGrey,
          elevation: 10.0,
          textTheme: TextTheme(
            title: TextStyle(
              color: Colors.black,
              fontSize: 32.0,
              fontWeight: FontWeight.w600,
            ),
          ),
          actionsIconTheme: IconThemeData(
            color: Colors.black,
            size: 48.0,
            opacity: 1.0,
          ),
        )),
        title: _title,
        initialRoute: HomePage.routeName,
        routes: {
          TryForm.routeName: (context) => TryForm(),
          HomePage.routeName: (context) => HomePage(),
        },
      ),
    );
  }
}
