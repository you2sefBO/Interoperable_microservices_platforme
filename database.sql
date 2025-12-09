-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : lun. 08 déc. 2025 à 21:23
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `database`
--

-- --------------------------------------------------------

--
-- Structure de la table `mesures`
--

CREATE TABLE `mesures` (
  `id` bigint(20) NOT NULL,
  `zone` varchar(255) DEFAULT NULL,
  `aqi` int(11) NOT NULL,
  `date_mesure` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `mesures`
--

INSERT INTO `mesures` (`id`, `zone`, `aqi`, `date_mesure`) VALUES
(1, 'Nord', 120, '2025-12-08 19:20:57'),
(2, 'Sud', 35, '2025-12-08 19:20:57'),
(3, 'Centre', 65, '2025-12-08 19:20:57');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `mesures`
--
ALTER TABLE `mesures`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `mesures`
--
ALTER TABLE `mesures`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

--
-- Structure de la table `alertes`
--

CREATE TABLE `alertes` (
  `id` varchar(255) NOT NULL,
  `zone` varchar(255) DEFAULT NULL,
  `niveau` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date_creation` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `alertes`
--

INSERT INTO `alertes` (`id`, `zone`, `niveau`, `description`, `date_creation`) VALUES
('alert-1', 'Nord', 'CRITICAL', 'Fuite de gaz secteur industriel', '2025-12-08 17:12:28'),
('alert-2', 'Centre', 'WARN', 'Manifestation en cours place centrale', '2025-12-08 17:12:28'),
('alert-3', 'Sud', 'INFO', 'Travaux de voirie mineurs', '2025-12-08 17:12:28');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `alertes`
--
ALTER TABLE `alertes`
  ADD PRIMARY KEY (`id`);
COMMIT;

--
-- Structure de la table `horaires`
--

CREATE TABLE `horaires` (
  `id` int(11) NOT NULL,
  `ligne_id` varchar(50) DEFAULT NULL,
  `arret` varchar(255) DEFAULT NULL,
  `heure_passage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `horaires`
--

INSERT INTO `horaires` (`id`, `ligne_id`, `arret`, `heure_passage`) VALUES
(1, 'ligne_1', 'Place Centrale', '10:00'),
(2, 'ligne_1', 'Gare Ferroviaire', '10:20'),
(3, 'ligne_2', 'Aéroport Terminal 1', '11:00'),
(4, 'ligne_2', 'Grand Stade', '11:45'),
(13, 'ligne_3', 'Cité Universitaire', '08:15'),
(14, 'ligne_3', 'Faculté des Sciences', '08:30'),
(15, 'ligne_3', 'Bibliothèque Univ.', '08:40'),
(16, 'ligne_3', 'Centre Commercial', '08:55'),
(17, 'ligne_3', 'Port de Plaisance', '09:05'),
(18, 'ligne_3', 'Plage Sud', '09:15');

-- --------------------------------------------------------

--
-- Structure de la table `lignes`
--

CREATE TABLE `lignes` (
  `id` varchar(50) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `lignes`
--

INSERT INTO `lignes` (`id`, `nom`, `description`) VALUES
('ligne_1', 'Ligne Centre-Gare', NULL),
('ligne_2', 'Ligne Aéroport-Stade', NULL),
('ligne_3', 'Ligne Campus-Plage', NULL);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `horaires`
--
ALTER TABLE `horaires`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ligne_id` (`ligne_id`);

--
-- Index pour la table `lignes`
--
ALTER TABLE `lignes`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `horaires`
--
ALTER TABLE `horaires`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `horaires`
--
ALTER TABLE `horaires`
  ADD CONSTRAINT `horaires_ibfk_1` FOREIGN KEY (`ligne_id`) REFERENCES `lignes` (`id`);
COMMIT;

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `id` varchar(255) NOT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `date_event` varchar(255) DEFAULT NULL,
  `lieu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id`, `titre`, `date_event`, `lieu`) VALUES
('e1', 'Concert de Jazz', '2025-12-01', 'Place centrale'),
('e2', 'Marathon International', '2026-03-20', 'Grand parc'),
('e3', 'Salon de la Tech', '2025-12-25', 'Palais des Congrès'),
('e_old', 'Fête de la Musique 2020', '2020-06-21', 'Vieux Port');

-- --------------------------------------------------------

--
-- Structure de la table `incidents`
--

CREATE TABLE `incidents` (
  `id` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `gravite` int(11) NOT NULL,
  `localisation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `incidents`
--

INSERT INTO `incidents` (`id`, `description`, `gravite`, `localisation`) VALUES
('i1', 'Nid de poule dangereux', 3, 'Rue de la République'),
('i2', 'Fuite d\'eau majeure', 5, 'Avenue de la Gare'),
('i3', 'Éclairage public en panne', 2, 'Place du Marché');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `incidents`
--
ALTER TABLE `incidents`
  ADD PRIMARY KEY (`id`);
COMMIT;


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
