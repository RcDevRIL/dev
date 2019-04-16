import 'package:flutter/material.dart';
import '../../Theme.dart' as Theme;
import '../../model/Patient.dart';

class PatientResumeBody extends StatelessWidget{

  final Patient patient;

  PatientResumeBody(this.patient);

  @override
  Widget build(BuildContext context) {
    return new Stack(
      children: <Widget>[
        new Container(
          color: Theme.Colors.planetPageBackground,
          child: new Center(
            child: new Hero(
              tag: 'patient-hero-${patient.id}',
              child: new Icon(Icons.ac_unit,
              size: Theme.Dimens.planetHeight)
            ),
          ),
        ),
      ],
    );
  }
}