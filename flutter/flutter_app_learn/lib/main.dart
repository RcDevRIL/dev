import 'package:flutter/material.dart';
import 'ui/home/RealHomePage.dart';
import 'ui/patient_pages/PatientResumePage.dart';

void main() {
  runApp(new MaterialApp(
    title: "Ergolife",
    initialRoute: '/',
    routes:{
      //'/' : (context) => RealHomePage(),
      PatientResumePage.routeName : (context) => PatientResumePage(),
    },
    home: new RealHomePage(),
  ));
}