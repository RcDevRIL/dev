import 'package:flutter/material.dart';

class InputTextField extends StatelessWidget{

  final String hint;
  final bool hide;
  final IconData icon;

  InputTextField({this.hide, this.hint, this.icon});

  @override
  Widget build(BuildContext context) {
    return new Container(
      decoration: new BoxDecoration(
        border: new Border(
          bottom: new BorderSide(
            width: 0.5,
            color: Colors.white24,
          ),
        ),
      ),
      child: new TextFormField(
        obscureText: hide,
        style: const TextStyle(
          color: Colors.white,
        ),
        decoration: new InputDecoration(
          icon: new Icon(
            icon,
            color: Colors.white,
          ),
          border: InputBorder.none,
          hintText: hint,
          hintStyle: const TextStyle(color: Colors.white, fontSize: 15.0),
          contentPadding: const EdgeInsets.only(
            top: 30.0, right: 30.0, bottom: 30.0, left: 5.0),
          )
        ),
    );
  }
}