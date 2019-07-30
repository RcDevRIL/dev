import 'package:custom_drawer/theme.dart';
import 'package:custom_drawer/widgets/custom-drawer.dart';
import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Custom Drawer',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          title: Text("Test custom drawer"),
          backgroundColor: drawerBackgroundColor,
          elevation: 0,
        ),
        body: Stack(
          children: <Widget>[
            Container(
              color: Colors.red,
            ),
            CustomDrawer(),
          ],
        ),
      ),
    );
  }
}
