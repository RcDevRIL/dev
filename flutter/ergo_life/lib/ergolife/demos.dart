// Copyright 2018 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

import 'package:flutter/material.dart';
//import '../demo/cupertino_text_field_demo.dart';
import '../demo/contacts_demo.dart';

import 'icons.dart';

class ErgoLifeFeature {
  const ErgoLifeFeature._({ this.name, this.icon });
  @required final String name;
  @required final IconData icon;

  @override
  bool operator ==(dynamic other) {
    if (identical(this, other))
      return true;
    if (runtimeType != other.runtimeType)
      return false;
    final ErgoLifeFeature typedOther = other;
    return typedOther.name == name && typedOther.icon == icon;
  }

  @override
  int get hashCode => hashValues(name, icon);

  @override
  String toString() {
    return '$runtimeType($name)';
  }
}

const ErgoLifeFeature _kDemos = ErgoLifeFeature._(
  name: 'Studies',
  icon: GalleryIcons.animation,
);

const ErgoLifeFeature _kStyle = ErgoLifeFeature._(
  name: 'Style',
  icon: GalleryIcons.custom_typography,
);

const ErgoLifeFeature _kMaterialComponents = ErgoLifeFeature._(
  name: 'Material',
  icon: GalleryIcons.category_mdc,
);

const ErgoLifeFeature _kCupertinoComponents = ErgoLifeFeature._(
  name: 'Cupertino',
  icon: GalleryIcons.phone_iphone,
);

const ErgoLifeFeature _kMedia = ErgoLifeFeature._(
  name: 'Media',
  icon: GalleryIcons.drive_video,
);

class GalleryDemo {
  const GalleryDemo({
    @required this.title,
    @required this.icon,
    this.subtitle,
    @required this.feature,
    @required this.routeName,
    this.documentationUrl,
    @required this.buildRoute,
  }) : assert(title != null),
       assert(feature != null),
       assert(routeName != null),
       assert(buildRoute != null);

  final String title;
  final IconData icon;
  final String subtitle;
  final ErgoLifeFeature feature;
  final String routeName;
  final WidgetBuilder buildRoute;
  final String documentationUrl;

  @override
  String toString() {
    return '$runtimeType($title $routeName)';
  }
}

List<GalleryDemo> _buildGalleryDemos() {
  final List<GalleryDemo> galleryDemos = <GalleryDemo>[
    // Demos
//    GalleryDemo(
//      title: 'Shrine',
//      subtitle: 'Basic shopping app',
//      icon: GalleryIcons.shrine,
//      feature: _kDemos,
//      routeName: ShrineDemo.routeName,
//      buildRoute: (BuildContext context) => const ShrineDemo(),
//    ),
    GalleryDemo(
      title: 'Contact profile',
      subtitle: 'Address book entry with a flexible appbar',
      icon: GalleryIcons.account_box,
      feature: _kDemos,
      routeName: ContactsDemo.routeName,
      buildRoute: (BuildContext context) => ContactsDemo(),
    ),

//    GalleryDemo(
//      title: 'Menus',
//      subtitle: 'Menu buttons and simple menus',
//      icon: GalleryIcons.more_vert,
//      feature: _kMaterialComponents,
//      routeName: MenuDemo.routeName,
//      documentationUrl: 'https://docs.flutter.io/flutter/material/PopupMenuButton-class.html',
//      buildRoute: (BuildContext context) => const MenuDemo(),
//    ),
//    GalleryDemo(
//      title: 'Navigation drawer',
//      subtitle: 'Navigation drawer with standard header',
//      icon: GalleryIcons.menu,
//      feature: _kMaterialComponents,
//      routeName: DrawerDemo.routeName,
//      documentationUrl: 'https://docs.flutter.io/flutter/material/Drawer-class.html',
//      buildRoute: (BuildContext context) => DrawerDemo(),
//    ),

//    GalleryDemo(
//      title: 'Text Fields',
//      icon: GalleryIcons.text_fields_alt,
//      feature: _kCupertinoComponents,
//      routeName: CupertinoTextFieldDemo.routeName,
//      buildRoute: (BuildContext context) => CupertinoTextFieldDemo(),
//    ),

  ];



  return galleryDemos;
}

final List<GalleryDemo> kAllGalleryDemos = _buildGalleryDemos();

final Set<ErgoLifeFeature> kAllGalleryDemoCategories =
  kAllGalleryDemos.map<ErgoLifeFeature>((GalleryDemo demo) => demo.feature).toSet();

final Map<ErgoLifeFeature, List<GalleryDemo>> kGalleryfeatureToDemos =
  Map<ErgoLifeFeature, List<GalleryDemo>>.fromIterable(
    kAllGalleryDemoCategories,
    value: (dynamic feature) {
      return kAllGalleryDemos.where((GalleryDemo demo) => demo.feature == feature).toList();
    },
  );

final Map<String, String> kDemoDocumentationUrl =
    Map<String, String>.fromIterable(
      kAllGalleryDemos.where((GalleryDemo demo) => demo.documentationUrl != null),
      key: (dynamic demo) => demo.routeName,
      value: (dynamic demo) => demo.documentationUrl,
    );
