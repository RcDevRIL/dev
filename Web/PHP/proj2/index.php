<?php
require __DIR__.'/vendor/autoload.php';

$smarty = new Smarty();

$smarty->debugging = true;

$smarty->setTemplateDir(__DIR__.'/templates/');
$smarty->setCompileDir(__DIR__.'/templates_c/');
$smarty->setConfigDir(__DIR__.'/configs/');
$smarty->setCacheDir(__DIR__.'/cache/');

$events = ['virus', 'remote work', 'confinement'];

$smarty->assign('site_title','Learn PHP and use Smarty!!');
$smarty->assign('subtitle','variables:');
$smarty->assign('events', ['virus', 'remote work', 'confinement']);


//** un-comment the following line to show the debug console
//$smarty->debugging = true;

$smarty->display('index.tpl');