import 'package:flutter/material.dart';
// import '../../Theme.dart' as Theme;
import '../../model/Patient.dart';

class PatientResumeBody extends StatelessWidget {
  final Patient patient;

  PatientResumeBody(this.patient);

  @override
  Widget build(BuildContext context) {
    return Stack(
      children: <Widget>[
//        Positioned(
//          top: 170.0,
//          left: (MediaQuery.of(context).size.width)/2,
//          child: new Container(
//            //color: Theme.Colors.appBarIconColor,
//            child: new Hero(
//                tag: 'patient-icon-${patient.id}',
//                child: new Container(
//                  width: 75.0,
//                  height: 75.0,
//                  decoration: new BoxDecoration(
//                    color: Colors.deepPurple,
//                    shape: BoxShape.circle,
//                  ),
//                ),
//              ),
//
//              ),
//          ),
        Center(child: Text('${patient.name} Hello')),

//        new Container(
//          color: Theme.Colors.planetPageBackground,
//          width: 250,
//          height: 50,
//          margin: const EdgeInsets.only(top: 120.0, left: 72.0),
//          constraints: new BoxConstraints.tightFor(),
//          child: new Column(
//            crossAxisAlignment: CrossAxisAlignment.start,
//            children: <Widget>[
//              new Row(
//                children: <Widget>[
//                  new Icon(Icons.account_circle, size: 20.0,
//                  color: Theme.Colors.planetPageBackground),
//                  //new Text(patient.name, style: Theme.TextStyles.planetDistance),
//                  //new Container(width: 24.0),
//                  //new Icon(Icons.add_box, size: 20.0,
//                  //color: Theme.Colors.planetPageBackground,),
        //                 //new Text(patient.pathologie, style: Theme.TextStyles.planetDistance),
      ],
    );

    //child: new Center(
    //  child: new Hero(
    //    tag: 'patient-icon-${patient.id}',
    //    child: //new Text(patient.id),
    //    new Image(
    //      image: new AssetImage(patient.img),
    //      height: 500,//,
    //      width: 500,//Theme.Dimens.planetWidth,
    //    ),
    //  ),
    //),
  }
}
