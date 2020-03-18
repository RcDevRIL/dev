<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;

    /**
     * @Route("/admin", name="back.")
     */
class BackController extends AbstractController
{
    /**
     * @Route("/connexion", name="login")
     */
    public function index()
    {
        return $this->render('back/index.html.twig', [
            'controller_name' => 'BackController',
        ]);
    }
    /**
     * @Route("/tableau-de-bord", name="dashboard")
     */
    public function dashboard()
    {
        return $this->render('back/index.html.twig', [
            'controller_name' => 'BackController',
        ]);
    }
}
