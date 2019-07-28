import 'package:flutter/material.dart';

class NavigationModel {
  String title;
  IconData iconData;
  NavigationModel({this.iconData, this.title});
}

List<NavigationModel> navigationItems = [
  NavigationModel(title: "Dashboard", iconData: Icons.insert_chart),
  NavigationModel(title: "Errors", iconData: Icons.error),
  NavigationModel(title: "Search", iconData: Icons.search),
  NavigationModel(title: "Notifications", iconData: Icons.notifications),
  NavigationModel(title: "Settings", iconData: Icons.settings),
];
