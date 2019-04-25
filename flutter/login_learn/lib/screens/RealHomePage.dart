import 'package:flutter/material.dart';
import '../ui_elements/CustomAppBar.dart';
import 'PatientList.dart';

class RealHomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      body: new HomePageBody(),
    );
  }

}

class HomePageBody extends StatefulWidget{
  @override
  _HomePageBodyState createState() => new _HomePageBodyState();
}

class _HomePageBodyState extends State<HomePageBody>{

  @override
  Widget build(BuildContext context) {
    return new Column(
      children: <Widget>[
        new CustomAppBar("Ergolife"),
        new PatientList(),
      ],
    );
  }

}