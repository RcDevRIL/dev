import 'package:custom_drawer/model/navigation_model.dart';
import 'package:custom_drawer/theme.dart';
import 'package:custom_drawer/widgets/collapsing-list-tile.dart';
import 'package:flutter/material.dart';

class CustomDrawer extends StatefulWidget {
  @override
  _CustomDrawerState createState() => _CustomDrawerState();
}

class _CustomDrawerState extends State<CustomDrawer>
    with SingleTickerProviderStateMixin {
  double maxWidth = 250.0;
  double minWidth = 70.0;
  bool isCollapsed = false;
  AnimationController _animationController;
  Animation<double> widthAnimation;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: 300),
    );
    widthAnimation = Tween<double>(begin: maxWidth, end: minWidth)
        .animate(_animationController);
  }

  @override
  Widget build(BuildContext context) {
    return AnimatedBuilder(
      animation: widthAnimation,
      builder: (context, w) {
        return Container(
          width: widthAnimation.value,
          color: drawerBackgroundColor,
          child: Column(
            children: <Widget>[
              SizedBox(
                height: 50.0,
              ),
              CollapsingListTile(
                title: "Test Name",
                icon: Icons.person,
                animationController: _animationController,
              ),
              Expanded(
                child: ListView.builder(
                  itemBuilder: (c, i) {
                    return CollapsingListTile(
                      title: navigationItems[i].title,
                      icon: navigationItems[i].iconData,
                      animationController: _animationController,
                    );
                  },
                  itemCount: navigationItems.length,
                ),
              ),
              FlatButton(
                onPressed: () {
                  setState(() {
                    isCollapsed = !isCollapsed;
                    isCollapsed
                        ? _animationController.forward()
                        : _animationController.reverse();
                  });
                },
                child: AnimatedIcon(
                  icon: AnimatedIcons.close_menu,
                  progress: _animationController,
                  color: Colors.white,
                  size: 70.0,
                ),
              ),
              SizedBox(
                height: 50.0,
              ),
            ],
          ),
        );
      },
    );
  }
}
