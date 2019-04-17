import 'package:flutter/material.dart';
import '../../model/Patients.dart';
import 'PatientResumeBody.dart';
import 'ResumeAppBar.dart';
import '../home/PatientRow.dart';

class PatientResumePage extends StatelessWidget{

  static const routeName = '/patientResume';


  @override
  Widget build(BuildContext context) {

    final PatientArgsForRoutes args = ModalRoute.of(context).settings.arguments;

    return new Scaffold(
      body: new Stack(
        children: <Widget>[
          new ResumeAppBar(),
          new PatientResumeBody(PatientDao.getPatientById(args.id)),
        ],
      ),
    );
  }
}