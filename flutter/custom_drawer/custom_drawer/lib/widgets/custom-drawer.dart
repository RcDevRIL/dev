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
  double maxWidth = 220.0;
  double minWidth = 72.0;
  bool isCollapsed = false;
  AnimationController _animationController;
  Animation<double> widthAnimation;
  int currentSelectedItem = 0;

  @override
  void initState() {
    super.initState();
    _animationController = AnimationController(
      vsync: this,
      duration: Duration(milliseconds: 350),
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
            // crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              CollapsingListTile(
                title: "Test Name",
                icon: Icons.person,
                animationController: _animationController,
              ),
              Divider(
                color: Colors.grey,
                height: 40.0,
                indent: 12.0,
                endIndent: 12.0,
              ),
              Expanded(
                child: ListView.separated(
                  separatorBuilder: (c, i) {
                    return Divider(
                      height: 12.0,
                    );
                  },
                  itemBuilder: (c, i) {
                    return CollapsingListTile(
                      onTap: () {
                        !isCollapsed
                            ? setState(() {
                                currentSelectedItem = i;
                              })
                            : setState(() {});
                      },
                      isSelected: currentSelectedItem == i,
                      title: navigationItems[i].title,
                      icon: navigationItems[i].iconData,
                      animationController: _animationController,
                    );
                  },
                  itemCount: navigationItems.length,
                ),
              ),
              FlatButton(
                padding: EdgeInsets.all(0.0),
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
                  color: selectedColor,
                  size: 50.0,
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
