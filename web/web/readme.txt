pages valides: (*.jsp)
    index
    web/
        partial_*
        index (login)
        request_action (page d'arrivée lors d'une modification de la base de données)
        dc
        menu-general
        record_*
          -  visualiser liste
          -  insérer
          -  supprimer
          -  modifier

servlets:
    <Context path="/rolandgarros"/>

    bddaction - controlleur ihm/bdd
    record - filtres les pages pour y ajouter les listes d'objets
    login

filter:
    ConnectedFilter