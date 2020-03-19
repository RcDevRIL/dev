<?php

namespace App\Controller;

use Cocur\Slugify\Slugify;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;

class FrontController extends AbstractController
{
    /**
     * @Route("/", name="front.index")
     */
    public function index()
    {
        $slug = (new Slugify())->slugify('Hello World');

        return $this->render('front/index.html.twig', [
            'controller_name'   => 'FrontController',
            'slug'              => $slug
        ]);
    }
}
