<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\EventRepository;

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
     * @param EventRepository $eventRepository
     * @return \Symfony\Component\HttpFoundation\Response
     */
    public function dashboard(EventRepository $eventRepository)
    {
        $events = $eventRepository->findAll();
        dump($events);
        return $this->render('back/dashboard.html.twig', [
            'events' => $events,
        ]);
    }
}
