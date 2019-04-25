import 'package:flutter/material.dart';
import 'custom_text_field.dart';

class FormContainer extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return new Container(
      margin: new EdgeInsets.symmetric(horizontal: 20.0),
      child: new Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          new Form(
            child: new Column(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: <Widget>[
                new InputTextField(
                  hint: "Username",
                  hide: false,
                  icon: Icons.person_outline,
                ),
                new InputTextField(
                  hint: "Password",
                  hide: true,
                  icon: Icons.lock_outline,
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
