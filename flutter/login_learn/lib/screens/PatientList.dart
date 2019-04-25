import 'package:flutter/material.dart';
import '../theme.dart' as Theme;
import '../model/Patients.dart';
import '../ui_elements/PatientRow.dart';

class PatientList extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Flexible(
      child: new Container(
        color: Theme.Colors.planetPageBackground,
        child: new ListView.builder(
          itemExtent: 160.0,
          itemCount: PatientDao.patients.length,
          itemBuilder: (context, index) => new PatientRow(PatientDao.patients[index]),
        ),
      ),
    );
  }
}