<?php

namespace App\DataFixtures;

use Doctrine\Bundle\FixturesBundle\Fixture;
use Doctrine\Common\Persistence\ObjectManager;
use App\Entity\Event;

class AppFixtures extends Fixture
{
    public function load(ObjectManager $manager)
    {
        $events = ['virus', 'remote work', 'confinement'];

        foreach ($events as $event){
            $e = new Event();
            $e->setName('$event');
            $e->setCreatedAt(new \DateTime());
            $manager->persist($e);
        }

        $manager->flush();
    }
}
