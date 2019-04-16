import 'package:flutter/material.dart';
import '../../Theme.dart' as Theme;
import '../../model/Patients.dart';
import 'PatientRow.dart';

class PatientList extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Flexible(
      child: new Container(
        color: Theme.Colors.planetPageBackground,
        child: new ListView.builder(
          itemExtent: 160.0,
          itemCount: PatientDao.patients.length,
          itemBuilder: (_, index) => new PatientRow(PatientDao.patients[index]),
        ),
      ),
    );
  }
}