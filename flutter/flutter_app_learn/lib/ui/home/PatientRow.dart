import 'package:flutter/material.dart';
import '../../Theme.dart' as Theme;
import '../../model/Patient.dart';
import '../patient_pages/PatientResumePage.dart';

class PatientRow extends StatelessWidget{

  final Patient patient;

  PatientRow(this.patient);

  @override
  Widget build(BuildContext context) {
    final patientThumbnail = new Container(
      alignment: new FractionalOffset(0.0, 0.5),
      margin: const EdgeInsets.only(left: 24.0),
      child: new Hero(
        tag: 'patient-icon-${patient.id}',
        child: new Container(
          width: 75.0,
          height: 75.0,
          decoration: new BoxDecoration(
            color: Colors.deepPurple,
            shape: BoxShape.circle,
          ),
        ),
      ),
    );

    final patientCard = new Container(
      margin: const EdgeInsets.only(left: 72.0, right: 24.0),
      decoration: new BoxDecoration(
        color: Theme.Colors.planetCard,
        shape: BoxShape.rectangle,
        borderRadius: new BorderRadius.circular(8.0),
        boxShadow: <BoxShadow>[
          new BoxShadow(
            color: Colors.black,
            blurRadius: 10.0,
            offset: new Offset(0.0, 10.0))
        ],
      ),
      child: new Container(
        margin: const EdgeInsets.only(top: 16.0, left: 72.0),
        constraints: new BoxConstraints.expand(),
        child: new Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            new Text(patient.name, style: Theme.TextStyles.planetTitle),
            new Text(patient.pathologie, style: Theme.TextStyles.planetLocation),
            new Container(
              color: const Color(0xFF00C6FF),
              width: 24.0,
              height: 1.0,
              margin: const EdgeInsets.symmetric(vertical: 8.0)
            ),
            new Row(
              children: <Widget>[
                new Icon(Icons.phone, size: 14.0,
                  color: Theme.Colors.planetDistance),
                new Text(
                  patient.numTel, style: Theme.TextStyles.planetDistance),
                new Container(width: 24.0),
                new Icon(Icons.add_location, size: 14.0,
                  color: Theme.Colors.planetDistance),
                new Text(
                  patient.id, style: Theme.TextStyles.planetDistance),
              ],
            )
          ],
        ),
      ),
    );

    return new Container(
      height: 120.0,
      margin: const EdgeInsets.only(top: 16.0, bottom: 8.0),
      child: new FlatButton(
          onPressed: () => Navigator.pushNamed(
              context,
              PatientResumePage.routeName,
              arguments: PatientArgsForRoutes(
                  '1'
              ),
          ),
          //_navigateTo(context, patient.id),

          child: new Stack(
            children: <Widget>[
              patientCard,
              patientThumbnail
            ],
          ),
      ),
    );
  }

  void _navigateTo(BuildContext context, String id) {
    //Routes.navigateTo(
      //context,
      //'/detail/${patient.id}',
      //transition: TransitionType.fadeIn
    //);
  }
  // user defined function
  void _showDialog(BuildContext context) {
    // flutter defined function
    showDialog(
      context: context,
      builder: (BuildContext context) {
        // return object of type Dialog
        return AlertDialog(
          title: new Text("Alert Dialog title"),
          content: new Text("Alert Dialog body"),
          actions: <Widget>[
            // usually buttons at the bottom of the dialog
            new FlatButton(
              child: new Text("Close"),
              onPressed: () {
                Navigator.of(context).pop();
              },
            ),
          ],
        );
      },
    );
  }
}
class PatientArgsForRoutes{
  final String id;

  PatientArgsForRoutes(this.id);
}