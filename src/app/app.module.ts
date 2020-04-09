import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RunListComponent } from './run-list/run-list.component';
import {BundleService} from "./services/bundleservice/bundle.service";
import {TopService} from "./services/topservice/top.service";
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatListModule} from "@angular/material/list";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import { RouterModule, Routes } from '@angular/router';
import {FormsModule} from "@angular/forms";
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatMenuModule} from "@angular/material/menu";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatSortModule} from "@angular/material/sort";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {MatTableModule} from "@angular/material/table";
import {MatTabsModule} from "@angular/material/tabs";
import {MatTooltipModule} from "@angular/material/tooltip";
import {MatSelectModule} from "@angular/material/select";
import {MatIconModule} from "@angular/material/icon";
import {MatStepperModule} from "@angular/material/stepper";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatRadioModule} from "@angular/material/radio";
import {MatChipsModule} from "@angular/material/chips";
import {MatNativeDateModule, MatRippleModule} from "@angular/material/core";
import {MatSliderModule} from "@angular/material/slider";
import {MatDialogModule} from "@angular/material/dialog";
import {MatDividerModule} from "@angular/material/divider";
import {PortalModule} from "@angular/cdk/portal";
import {A11yModule} from "@angular/cdk/a11y";
import {CdkStepperModule} from "@angular/cdk/stepper";
import {CdkTableModule} from "@angular/cdk/table";
import { DeckCompareComponent } from './deckpages/deck-compare/deck-compare.component';
import { StandardCardsComponent } from './deckpages/cards/standard-cards/standard-cards.component';
import { DragonCardsComponent } from './deckpages/cards/dragon-cards/dragon-cards.component';
import { NaturiaCardsComponent } from './deckpages/cards/naturia-cards/naturia-cards.component';
import { SpellcasterCardsComponent } from './deckpages/cards/spellcaster-cards/spellcaster-cards.component';
import { ToonCardsComponent } from './deckpages/cards/toon-cards/toon-cards.component';
import { AquaCardsComponent } from './deckpages/cards/aqua-cards/aqua-cards.component';
import { ZombieCardsComponent } from './deckpages/cards/zombie-cards/zombie-cards.component';
import { FiendCardsComponent } from './deckpages/cards/fiend-cards/fiend-cards.component';
import { MachineCardsComponent } from './deckpages/cards/machine-cards/machine-cards.component';
import { WarriorCardsComponent } from './deckpages/cards/warrior-cards/warrior-cards.component';
import { InsectCardsComponent } from './deckpages/cards/insect-cards/insect-cards.component';
import { PlantCardsComponent } from './deckpages/cards/plant-cards/plant-cards.component';
import { MegatypeCardsComponent } from './deckpages/cards/megatype-cards/megatype-cards.component';
import { IncrementCardsComponent } from './deckpages/cards/increment-cards/increment-cards.component';
import { CreatorCardsComponent } from './deckpages/cards/creator-cards/creator-cards.component';
import { OjamaCardsComponent } from './deckpages/cards/ojama-cards/ojama-cards.component';
import { ExodiaCardsComponent } from './deckpages/cards/exodia-cards/exodia-cards.component';
import { AscendedOneCardsComponent } from './deckpages/cards/ascended-one-cards/ascended-one-cards.component';
import { AscendedTwoCardsComponent } from './deckpages/cards/ascended-two-cards/ascended-two-cards.component';
import { AscendedThreeCardsComponent } from './deckpages/cards/ascended-three-cards/ascended-three-cards.component';
import { PharaohOneCardsComponent } from './deckpages/cards/pharaoh-one-cards/pharaoh-one-cards.component';
import { PharaohTwoCardsComponent } from './deckpages/cards/pharaoh-two-cards/pharaoh-two-cards.component';
import { PharaohThreeCardsComponent } from './deckpages/cards/pharaoh-three-cards/pharaoh-three-cards.component';
import { PharaohFourCardsComponent } from './deckpages/cards/pharaoh-four-cards/pharaoh-four-cards.component';
import { PharaohFiveCardsComponent } from './deckpages/cards/pharaoh-five-cards/pharaoh-five-cards.component';
import { RandomSmallCardsComponent } from './deckpages/cards/random-small-cards/random-small-cards.component';
import { RandomBigCardsComponent } from './deckpages/cards/random-big-cards/random-big-cards.component';
import { UpgradeDeckCardsComponent } from './deckpages/cards/upgrade-deck-cards/upgrade-deck-cards.component';
import { MetronomeCardsComponent } from './deckpages/cards/metronome-cards/metronome-cards.component';
import { StandardRelicsComponent } from './deckpages/relics/standard-relics/standard-relics.component';
import { StandardPotionsComponent } from './deckpages/potions/standard-potions/standard-potions.component';
import { StandardNeowComponent } from './deckpages/neow/standard-neow/standard-neow.component';
import {StandardInfoComponent} from "./deckpages/info/standard-info/standard-info.component";
import { DragonRelicsComponent } from './deckpages/relics/dragon-relics/dragon-relics.component';
import { NaturiaRelicsComponent } from './deckpages/relics/naturia-relics/naturia-relics.component';
import { SpellcasterRelicsComponent } from './deckpages/relics/spellcaster-relics/spellcaster-relics.component';
import { ToonRelicsComponent } from './deckpages/relics/toon-relics/toon-relics.component';
import { ZombieRelicsComponent } from './deckpages/relics/zombie-relics/zombie-relics.component';
import { AquaRelicsComponent } from './deckpages/relics/aqua-relics/aqua-relics.component';
import { FiendRelicsComponent } from './deckpages/relics/fiend-relics/fiend-relics.component';
import { MachineRelicsComponent } from './deckpages/relics/machine-relics/machine-relics.component';
import { WarriorRelicsComponent } from './deckpages/relics/warrior-relics/warrior-relics.component';
import { InsectRelicsComponent } from './deckpages/relics/insect-relics/insect-relics.component';
import { PlantRelicsComponent } from './deckpages/relics/plant-relics/plant-relics.component';
import { MegatypeRelicsComponent } from './deckpages/relics/megatype-relics/megatype-relics.component';
import { IncrementRelicsComponent } from './deckpages/relics/increment-relics/increment-relics.component';
import { CreatorRelicsComponent } from './deckpages/relics/creator-relics/creator-relics.component';
import { OjamaRelicsComponent } from './deckpages/relics/ojama-relics/ojama-relics.component';
import { ExodiaRelicsComponent } from './deckpages/relics/exodia-relics/exodia-relics.component';
import { AscendedOneRelicsComponent } from './deckpages/relics/ascendedOne-relics/ascended-one-relics.component';
import { AscendedTwoRelicsComponent } from './deckpages/relics/ascendedTwo-relics/ascended-two-relics.component';
import { AscendedThreeRelicsComponent } from './deckpages/relics/ascendedThree-relics/ascended-three-relics.component';
import { PharaohOneRelicsComponent } from './deckpages/relics/pharaohOne-relics/pharaoh-one-relics.component';
import { PharaohTwoRelicsComponent } from './deckpages/relics/pharaohTwo-relics/pharaoh-two-relics.component';
import { PharaohThreeRelicsComponent } from './deckpages/relics/pharaohThree-relics/pharaoh-three-relics.component';
import { PharaohFourRelicsComponent } from './deckpages/relics/pharaohFour-relics/pharaoh-four-relics.component';
import { PharaohFiveRelicsComponent } from './deckpages/relics/pharaohFive-relics/pharaoh-five-relics.component';
import { RandomSmallRelicsComponent } from './deckpages/relics/randomSmall-relics/random-small-relics.component';
import { RandomBigRelicsComponent } from './deckpages/relics/randomBig-relics/random-big-relics.component';
import { UpgradeDeckRelicsComponent } from './deckpages/relics/upgradeDeck-relics/upgrade-deck-relics.component';
import { MetronomeRelicsComponent } from './deckpages/relics/metronome-relics/metronome-relics.component';
import { DragonPotionsComponent } from './deckpages/potions/dragon-potions/dragon-potions.component';
import { NaturiaPotionsComponent } from './deckpages/potions/naturia-potions/naturia-potions.component';
import { SpellcasterPotionsComponent } from './deckpages/potions/spellcaster-potions/spellcaster-potions.component';
import { ToonPotionsComponent } from './deckpages/potions/toon-potions/toon-potions.component';
import { ZombiePotionsComponent } from './deckpages/potions/zombie-potions/zombie-potions.component';
import { AquaPotionsComponent } from './deckpages/potions/aqua-potions/aqua-potions.component';
import { FiendPotionsComponent } from './deckpages/potions/fiend-potions/fiend-potions.component';
import { MachinePotionsComponent } from './deckpages/potions/machine-potions/machine-potions.component';
import { InsectPotionsComponent } from './deckpages/potions/insect-potions/insect-potions.component';
import { PlantPotionsComponent } from './deckpages/potions/plant-potions/plant-potions.component';
import { MegatypePotionsComponent } from './deckpages/potions/megatype-potions/megatype-potions.component';
import { IncrementPotionsComponent } from './deckpages/potions/increment-potions/increment-potions.component';
import { CreatorPotionsComponent } from './deckpages/potions/creator-potions/creator-potions.component';
import { OjamaPotionsComponent } from './deckpages/potions/ojama-potions/ojama-potions.component';
import { ExodiaPotionsComponent } from './deckpages/potions/exodia-potions/exodia-potions.component';
import { AscendedOnePotionsComponent } from './deckpages/potions/ascendedOne-potions/ascended-one-potions.component';
import { AscendedTwoPotionsComponent } from './deckpages/potions/ascendedTwo-potions/ascended-two-potions.component';
import { AscendedThreePotionsComponent } from './deckpages/potions/ascendedThree-potions/ascended-three-potions.component';
import { PharaohOnePotionsComponent } from './deckpages/potions/pharaohOne-potions/pharaoh-one-potions.component';
import { PharaohTwoPotionsComponent } from './deckpages/potions/pharaohTwo-potions/pharaoh-two-potions.component';
import { PharaohThreePotionsComponent } from './deckpages/potions/pharaohThree-potions/pharaoh-three-potions.component';
import { PharaohFourPotionsComponent } from './deckpages/potions/pharaohFour-potions/pharaoh-four-potions.component';
import { PharaohFivePotionsComponent } from './deckpages/potions/pharaohFive-potions/pharaoh-five-potions.component';
import { RandomSmallPotionsComponent } from './deckpages/potions/randomSmall-potions/random-small-potions.component';
import { RandomBigPotionsComponent } from './deckpages/potions/randomBig-potions/random-big-potions.component';
import { UpgradeDeckPotionsComponent } from './deckpages/potions/upgradeDeck-potions/upgrade-deck-potions.component';
import { MetronomePotionsComponent } from './deckpages/potions/metronome-potions/metronome-potions.component';
import { DragonInfoComponent } from './deckpages/info/dragon-info/dragon-info.component';
import { NaturiaInfoComponent } from './deckpages/info/naturia-info/naturia-info.component';
import { SpellcasterInfoComponent } from './deckpages/info/spellcaster-info/spellcaster-info.component';
import { ToonInfoComponent } from './deckpages/info/toon-info/toon-info.component';
import { ZombieInfoComponent } from './deckpages/info/zombie-info/zombie-info.component';
import { AquaInfoComponent } from './deckpages/info/aqua-info/aqua-info.component';
import { FiendInfoComponent } from './deckpages/info/fiend-info/fiend-info.component';
import { MachineInfoComponent } from './deckpages/info/machine-info/machine-info.component';
import { WarriorInfoComponent } from './deckpages/info/warrior-info/warrior-info.component';
import { InsectInfoComponent } from './deckpages/info/insect-info/insect-info.component';
import { PlantInfoComponent } from './deckpages/info/plant-info/plant-info.component';
import { MegatypeInfoComponent } from './deckpages/info/megatype-info/megatype-info.component';
import { IncrementInfoComponent } from './deckpages/info/increment-info/increment-info.component';
import { CreatorInfoComponent } from './deckpages/info/creator-info/creator-info.component';
import { OjamaInfoComponent } from './deckpages/info/ojama-info/ojama-info.component';
import { ExodiaInfoComponent } from './deckpages/info/exodia-info/exodia-info.component';
import { AscendedOneInfoComponent } from './deckpages/info/ascendedOne-info/ascended-one-info.component';
import { AscendedTwoInfoComponent } from './deckpages/info/ascendedTwo-info/ascended-two-info.component';
import { AscendedThreeInfoComponent } from './deckpages/info/ascendedThree-info/ascended-three-info.component';
import { PharaohOneInfoComponent } from './deckpages/info/pharaohOne-info/pharaoh-one-info.component';
import { PharaohTwoInfoComponent } from './deckpages/info/pharaohTwo-info/pharaoh-two-info.component';
import { PharaohThreeInfoComponent } from './deckpages/info/pharaohThree-info/pharaoh-three-info.component';
import { PharaohFourInfoComponent } from './deckpages/info/pharaohFour-info/pharaoh-four-info.component';
import { PharaohFiveInfoComponent } from './deckpages/info/pharaohFive-info/pharaoh-five-info.component';
import { RandomSmallInfoComponent } from './deckpages/info/randomSmall-info/random-small-info.component';
import { RandomBigInfoComponent } from './deckpages/info/randomBig-info/random-big-info.component';
import { UpgradeDeckInfoComponent } from './deckpages/info/upgradeDeck-info/upgrade-deck-info.component';
import { MetronomeInfoComponent } from './deckpages/info/metronome-info/metronome-info.component';
import { DragonNeowComponent } from './deckpages/neow/dragon-neow/dragon-neow.component';
import { NaturiaNeowComponent } from './deckpages/neow/naturia-neow/naturia-neow.component';
import { SpellcasterNeowComponent } from './deckpages/neow/spellcaster-neow/spellcaster-neow.component';
import { ToonNeowComponent } from './deckpages/neow/toon-neow/toon-neow.component';
import { ZombieNeowComponent } from './deckpages/neow/zombie-neow/zombie-neow.component';
import { AquaNeowComponent } from './deckpages/neow/aqua-neow/aqua-neow.component';
import { FiendNeowComponent } from './deckpages/neow/fiend-neow/fiend-neow.component';
import { MachineNeowComponent } from './deckpages/neow/machine-neow/machine-neow.component';
import { WarriorNeowComponent } from './deckpages/neow/warrior-neow/warrior-neow.component';
import { InsectNeowComponent } from './deckpages/neow/insect-neow/insect-neow.component';
import { PlantNeowComponent } from './deckpages/neow/plant-neow/plant-neow.component';
import { MegatypeNeowComponent } from './deckpages/neow/megatype-neow/megatype-neow.component';
import { IncrementNeowComponent } from './deckpages/neow/increment-neow/increment-neow.component';
import { CreatorNeowComponent } from './deckpages/neow/creator-neow/creator-neow.component';
import { OjamaNeowComponent } from './deckpages/neow/ojama-neow/ojama-neow.component';
import { ExodiaNeowComponent } from './deckpages/neow/exodia-neow/exodia-neow.component';
import { AscendedOneNeowComponent } from './deckpages/neow/ascendedOne-neow/ascended-one-neow.component';
import { AscendedTwoNeowComponent } from './deckpages/neow/ascendedTwo-neow/ascended-two-neow.component';
import { AscendedThreeNeowComponent } from './deckpages/neow/ascendedThree-neow/ascended-three-neow.component';
import { PharaohOneNeowComponent } from './deckpages/neow/pharaohOne-neow/pharaoh-one-neow.component';
import { PharaohTwoNeowComponent } from './deckpages/neow/pharaohTwo-neow/pharaoh-two-neow.component';
import { PharaohThreeNeowComponent } from './deckpages/neow/pharaohThree-neow/pharaoh-three-neow.component';
import { PharaohFourNeowComponent } from './deckpages/neow/pharaohFour-neow/pharaoh-four-neow.component';
import { PharaohFiveNeowComponent } from './deckpages/neow/pharaohFive-neow/pharaoh-five-neow.component';
import { RandomSmallNeowComponent } from './deckpages/neow/randomSmall-neow/random-small-neow.component';
import { RandomBigNeowComponent } from './deckpages/neow/randomBig-neow/random-big-neow.component';
import { UpgradeDeckNeowComponent } from './deckpages/neow/upgradeDeck-neow/upgrade-deck-neow.component';
import { MetronomeNeowComponent } from './deckpages/neow/metronome-neow/metronome-neow.component';
import { AllNeowComponent } from './deckpages/neow/all-neow/all-neow.component';
import { AllRelicsComponent } from './deckpages/relics/all-relics/all-relics.component';
import { AllPotionsComponent } from './deckpages/potions/all-potions/all-potions.component';
import { AllCardsComponent } from './deckpages/cards/all-cards/all-cards.component';
import { WarriorPotionsComponent } from './deckpages/potions/warrior-potions/warrior-potions.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/run-list', pathMatch: 'full' },
  {
    path: 'all-compare',
    component: DeckCompareComponent
  },
  {
    path: 'all-cards',
    component: AllCardsComponent
  },
  {
    path: 'all-relics',
    component: AllRelicsComponent
  },
  {
    path: 'all-potions',
    component: AllPotionsComponent
  },
  {
    path: 'all-neow',
    component: AllNeowComponent
  },
  {
    path: 'standard-info',
    component: StandardInfoComponent
  },
  {
    path: 'standard-cards',
    component: StandardCardsComponent
  },
  {
    path: 'standard-relics',
    component: StandardRelicsComponent
  },
  {
    path: 'standard-potions',
    component: StandardPotionsComponent
  },
  {
    path: 'standard-neow',
    component: StandardNeowComponent
  },
  {
    path: 'dragon-info',
    component: DragonInfoComponent
  },
  {
    path: 'dragon-cards',
    component: DragonCardsComponent
  },
  {
    path: 'dragon-relics',
    component: DragonRelicsComponent
  },
  {
    path: 'dragon-potions',
    component: DragonPotionsComponent
  },
  {
    path: 'dragon-neow',
    component: DragonNeowComponent
  },
  {
    path: 'naturia-info',
    component: NaturiaInfoComponent
  },
  {
    path: 'naturia-cards',
    component: NaturiaCardsComponent
  },
  {
    path: 'naturia-relics',
    component: NaturiaRelicsComponent
  },
  {
    path: 'naturia-potions',
    component: NaturiaPotionsComponent
  },
  {
    path: 'naturia-neow',
    component: NaturiaNeowComponent
  },
  {
    path: 'spellcaster-info',
    component: SpellcasterInfoComponent
  },
  {
    path: 'spellcaster-cards',
    component: SpellcasterCardsComponent
  },
  {
    path: 'spellcaster-relics',
    component: SpellcasterRelicsComponent
  },
  {
    path: 'spellcaster-potions',
    component: SpellcasterPotionsComponent
  },
  {
    path: 'spellcaster-neow',
    component: SpellcasterNeowComponent
  },
  {
    path: 'toon-info',
    component: ToonInfoComponent
  },
  {
    path: 'toon-cards',
    component: ToonCardsComponent
  },
  {
    path: 'toon-relics',
    component: ToonRelicsComponent
  },
  {
    path: 'toon-potions',
    component: ToonPotionsComponent
  },
  {
    path: 'toon-neow',
    component: ToonNeowComponent
  },
  {
    path: 'zombie-info',
    component: ZombieInfoComponent
  },
  {
    path: 'zombie-cards',
    component: ZombieCardsComponent
  },
  {
    path: 'zombie-relics',
    component: ZombieRelicsComponent
  },
  {
    path: 'zombie-potions',
    component: ZombiePotionsComponent
  },
  {
    path: 'zombie-neow',
    component: ZombieNeowComponent
  },
  {
    path: 'aqua-info',
    component: AquaInfoComponent
  },
  {
    path: 'aqua-cards',
    component: AquaCardsComponent
  },
  {
    path: 'aqua-relics',
    component: AquaRelicsComponent
  },
  {
    path: 'aqua-potions',
    component: AquaPotionsComponent
  },
  {
    path: 'aqua-neow',
    component: AquaNeowComponent
  },
  {
    path: 'fiend-info',
    component: FiendInfoComponent
  },
  {
    path: 'fiend-cards',
    component: FiendCardsComponent
  },
  {
    path: 'fiend-relics',
    component: FiendRelicsComponent
  },
  {
    path: 'fiend-potions',
    component: FiendPotionsComponent
  },
  {
    path: 'fiend-neow',
    component: FiendNeowComponent
  },
  {
    path: 'machine-info',
    component: MachineInfoComponent
  },
  {
    path: 'machine-cards',
    component: MachineCardsComponent
  },
  {
    path: 'machine-relics',
    component: MachineRelicsComponent
  },
  {
    path: 'machine-potions',
    component: MachinePotionsComponent
  },
  {
    path: 'machine-neow',
    component: MachineNeowComponent
  },
  {
    path: 'warrior-info',
    component: WarriorInfoComponent
  },
  {
    path: 'warrior-cards',
    component: WarriorCardsComponent
  },
  {
    path: 'warrior-relics',
    component: WarriorRelicsComponent
  },
  {
    path: 'warrior-potions',
    component: WarriorPotionsComponent
  },
  {
    path: 'warrior-neow',
    component: WarriorNeowComponent
  },
  {
    path: 'insect-info',
    component: InsectInfoComponent
  },
  {
    path: 'insect-cards',
    component: InsectCardsComponent
  },
  {
    path: 'insect-relics',
    component: InsectRelicsComponent
  },
  {
    path: 'insect-potions',
    component: InsectPotionsComponent
  },
  {
    path: 'insect-neow',
    component: InsectNeowComponent
  },
  {
    path: 'plant-info',
    component: PlantInfoComponent
  },
  {
    path: 'plant-cards',
    component: PlantCardsComponent
  },
  {
    path: 'plant-relics',
    component: PlantRelicsComponent
  },
  {
    path: 'plant-potions',
    component: PlantPotionsComponent
  },
  {
    path: 'plant-neow',
    component: PlantNeowComponent
  },
  {
    path: 'megatype-info',
    component: MegatypeInfoComponent
  },
  {
    path: 'megatype-cards',
    component: MegatypeCardsComponent
  },
  {
    path: 'megatype-relics',
    component: MegatypeRelicsComponent
  },
  {
    path: 'megatype-potions',
    component: MegatypePotionsComponent
  },
  {
    path: 'megatype-neow',
    component: MegatypeNeowComponent
  },
  {
    path: 'increment-info',
    component: IncrementInfoComponent
  },
  {
    path: 'increment-cards',
    component: IncrementCardsComponent
  },
  {
    path: 'increment-relics',
    component: IncrementRelicsComponent
  },
  {
    path: 'increment-potions',
    component: IncrementPotionsComponent
  },
  {
    path: 'increment-neow',
    component: IncrementNeowComponent
  },
  {
    path: 'creator-info',
    component: CreatorInfoComponent
  },
  {
    path: 'creator-cards',
    component: CreatorCardsComponent
  },
  {
    path: 'creator-relics',
    component: CreatorRelicsComponent
  },
  {
    path: 'creator-potions',
    component: CreatorPotionsComponent
  },
  {
    path: 'creator-neow',
    component: CreatorNeowComponent
  },
  {
    path: 'ojama-info',
    component: OjamaInfoComponent
  },
  {
    path: 'ojama-cards',
    component: OjamaCardsComponent
  },
  {
    path: 'ojama-relics',
    component: OjamaRelicsComponent
  },
  {
    path: 'ojama-potions',
    component: OjamaPotionsComponent
  },
  {
    path: 'ojama-neow',
    component: OjamaNeowComponent
  },
  {
    path: 'exodia-info',
    component: ExodiaInfoComponent
  },
  {
    path: 'exodia-cards',
    component: ExodiaCardsComponent
  },
  {
    path: 'exodia-relics',
    component: ExodiaRelicsComponent
  },
  {
    path: 'exodia-potions',
    component: ExodiaPotionsComponent
  },
  {
    path: 'exodia-neow',
    component: ExodiaNeowComponent
  },
  {
    path: 'ascendedOne-info',
    component: AscendedOneInfoComponent
  },
  {
    path: 'ascendedOne-cards',
    component: AscendedOneCardsComponent
  },
  {
    path: 'ascendedOne-relics',
    component: AscendedOneRelicsComponent
  },
  {
    path: 'ascendedOne-potions',
    component: AscendedOnePotionsComponent
  },
  {
    path: 'ascendedOne-neow',
    component: AscendedOneNeowComponent
  },
  {
    path: 'ascendedTwo-info',
    component: AscendedTwoInfoComponent
  },
  {
    path: 'ascendedTwo-cards',
    component: AscendedTwoCardsComponent
  },
  {
    path: 'ascendedTwo-relics',
    component: AscendedTwoRelicsComponent
  },
  {
    path: 'ascendedTwo-potions',
    component: AscendedTwoPotionsComponent
  },
  {
    path: 'ascendedTwo-neow',
    component: AscendedTwoNeowComponent
  },
  {
    path: 'ascendedThree-info',
    component: AscendedThreeInfoComponent
  },
  {
    path: 'ascendedThree-cards',
    component: AscendedThreeCardsComponent
  },
  {
    path: 'ascendedThree-relics',
    component: AscendedThreeRelicsComponent
  },
  {
    path: 'ascendedThree-potions',
    component: AscendedThreePotionsComponent
  },
  {
    path: 'ascendedThree-neow',
    component: AscendedThreeNeowComponent
  },
  {
    path: 'pharaohOne-info',
    component: PharaohOneInfoComponent
  },
  {
    path: 'pharaohOne-cards',
    component: PharaohOneCardsComponent
  },
  {
    path: 'pharaohOne-relics',
    component: PharaohOneRelicsComponent
  },
  {
    path: 'pharaohOne-potions',
    component: PharaohOnePotionsComponent
  },
  {
    path: 'pharaohOne-neow',
    component: PharaohOneNeowComponent
  },
  {
    path: 'pharaohTwo-info',
    component: PharaohTwoInfoComponent
  },
  {
    path: 'pharaohTwo-cards',
    component: PharaohTwoCardsComponent
  },
  {
    path: 'pharaohTwo-relics',
    component: PharaohTwoRelicsComponent
  },
  {
    path: 'pharaohTwo-potions',
    component: PharaohTwoPotionsComponent
  },
  {
    path: 'pharaohTwo-neow',
    component: PharaohTwoNeowComponent
  },
  {
    path: 'pharaohThree-info',
    component: PharaohThreeInfoComponent
  },
  {
    path: 'pharaohThree-cards',
    component: PharaohThreeCardsComponent
  },
  {
    path: 'pharaohThree-relics',
    component: PharaohThreeRelicsComponent
  },
  {
    path: 'pharaohThree-potions',
    component: PharaohThreePotionsComponent
  },
  {
    path: 'pharaohThree-neow',
    component: PharaohThreeNeowComponent
  },
  {
    path: 'pharaohFour-info',
    component: PharaohFourInfoComponent
  },
  {
    path: 'pharaohFour-cards',
    component: PharaohFourCardsComponent
  },
  {
    path: 'pharaohFour-relics',
    component: PharaohFourRelicsComponent
  },
  {
    path: 'pharaohFour-potions',
    component: PharaohFourPotionsComponent
  },
  {
    path: 'pharaohFour-neow',
    component: PharaohFourNeowComponent
  },
  {
    path: 'pharaohFour-info',
    component: PharaohFourInfoComponent
  },
  {
    path: 'pharaohFour-cards',
    component: PharaohFourCardsComponent
  },
  {
    path: 'pharaohFour-relics',
    component: PharaohFourRelicsComponent
  },
  {
    path: 'pharaohFour-potions',
    component: PharaohFourPotionsComponent
  },
  {
    path: 'pharaohFour-neow',
    component: PharaohFourNeowComponent
  },
  {
    path: 'pharaohFive-info',
    component: PharaohFiveInfoComponent
  },
  {
    path: 'pharaohFive-cards',
    component: PharaohFiveCardsComponent
  },
  {
    path: 'pharaohFive-relics',
    component: PharaohFiveRelicsComponent
  },
  {
    path: 'pharaohFive-potions',
    component: PharaohFivePotionsComponent
  },
  {
    path: 'pharaohFive-neow',
    component: PharaohFiveNeowComponent
  },
  {
    path: 'randomSmall-info',
    component: RandomSmallInfoComponent
  },
  {
    path: 'randomSmall-cards',
    component: RandomSmallCardsComponent
  },
  {
    path: 'randomSmall-relics',
    component: RandomSmallRelicsComponent
  },
  {
    path: 'randomSmall-potions',
    component: RandomSmallPotionsComponent
  },
  {
    path: 'randomSmall-neow',
    component: RandomSmallNeowComponent
  },
  {
    path: 'randomBig-info',
    component: RandomBigInfoComponent
  },
  {
    path: 'randomBig-cards',
    component: RandomBigCardsComponent
  },
  {
    path: 'randomBig-relics',
    component: RandomBigRelicsComponent
  },
  {
    path: 'randomBig-potions',
    component: RandomBigPotionsComponent
  },
  {
    path: 'randomBig-neow',
    component: RandomBigNeowComponent
  },
  {
    path: 'upgradeDeck-info',
    component: UpgradeDeckInfoComponent
  },
  {
    path: 'upgradeDeck-cards',
    component: UpgradeDeckCardsComponent
  },
  {
    path: 'upgradeDeck-relics',
    component: UpgradeDeckRelicsComponent
  },
  {
    path: 'upgradeDeck-potions',
    component: UpgradeDeckPotionsComponent
  },
  {
    path: 'upgradeDeck-neow',
    component: UpgradeDeckNeowComponent
  },
  {
    path: 'metronome-info',
    component: MetronomeInfoComponent
  },
  {
    path: 'metronome-cards',
    component: MetronomeCardsComponent
  },
  {
    path: 'metronome-relics',
    component: MetronomeRelicsComponent
  },
  {
    path: 'metronome-potions',
    component: MetronomePotionsComponent
  },
  {
    path: 'metronome-neow',
    component: MetronomeNeowComponent
  },
  {
    path: 'run-list',
    component: RunListComponent
  }
];

@NgModule({
  declarations: [
    AppComponent,
    RunListComponent,
    DeckCompareComponent,
    StandardCardsComponent,
    DragonCardsComponent,
    NaturiaCardsComponent,
    SpellcasterCardsComponent,
    ToonCardsComponent,
    AquaCardsComponent,
    ZombieCardsComponent,
    FiendCardsComponent,
    MachineCardsComponent,
    WarriorCardsComponent,
    InsectCardsComponent,
    PlantCardsComponent,
    MegatypeCardsComponent,
    IncrementCardsComponent,
    CreatorCardsComponent,
    OjamaCardsComponent,
    ExodiaCardsComponent,
    AscendedOneCardsComponent,
    AscendedTwoCardsComponent,
    AscendedThreeCardsComponent,
    PharaohOneCardsComponent,
    PharaohTwoCardsComponent,
    PharaohThreeCardsComponent,
    PharaohFourCardsComponent,
    PharaohFiveCardsComponent,
    RandomSmallCardsComponent,
    RandomBigCardsComponent,
    UpgradeDeckCardsComponent,
    MetronomeCardsComponent,
    StandardPotionsComponent,
    StandardNeowComponent,
    StandardInfoComponent,
    StandardRelicsComponent,
    DragonRelicsComponent,
    NaturiaRelicsComponent,
    SpellcasterRelicsComponent,
    ToonRelicsComponent,
    ZombieRelicsComponent,
    AquaRelicsComponent,
    FiendRelicsComponent,
    MachineRelicsComponent,
    WarriorRelicsComponent,
    InsectRelicsComponent,
    PlantRelicsComponent,
    MegatypeRelicsComponent,
    IncrementRelicsComponent,
    CreatorRelicsComponent,
    OjamaRelicsComponent,
    ExodiaRelicsComponent,
    AscendedOneRelicsComponent,
    AscendedTwoRelicsComponent,
    AscendedThreeRelicsComponent,
    PharaohOneRelicsComponent,
    PharaohTwoRelicsComponent,
    PharaohThreeRelicsComponent,
    PharaohFourRelicsComponent,
    PharaohFiveRelicsComponent,
    RandomSmallRelicsComponent,
    RandomBigRelicsComponent,
    UpgradeDeckRelicsComponent,
    MetronomeRelicsComponent,
    DragonPotionsComponent,
    NaturiaPotionsComponent,
    SpellcasterPotionsComponent,
    ToonPotionsComponent,
    ZombiePotionsComponent,
    AquaPotionsComponent,
    FiendPotionsComponent,
    MachinePotionsComponent,
    InsectPotionsComponent,
    PlantPotionsComponent,
    MegatypePotionsComponent,
    IncrementPotionsComponent,
    CreatorPotionsComponent,
    OjamaPotionsComponent,
    ExodiaPotionsComponent,
    AscendedOnePotionsComponent,
    AscendedTwoPotionsComponent,
    AscendedThreePotionsComponent,
    PharaohOnePotionsComponent,
    PharaohTwoPotionsComponent,
    PharaohThreePotionsComponent,
    PharaohFourPotionsComponent,
    PharaohFivePotionsComponent,
    RandomSmallPotionsComponent,
    RandomBigPotionsComponent,
    UpgradeDeckPotionsComponent,
    MetronomePotionsComponent,
    DragonInfoComponent,
    NaturiaInfoComponent,
    SpellcasterInfoComponent,
    ToonInfoComponent,
    ZombieInfoComponent,
    AquaInfoComponent,
    FiendInfoComponent,
    MachineInfoComponent,
    WarriorInfoComponent,
    InsectInfoComponent,
    PlantInfoComponent,
    MegatypeInfoComponent,
    IncrementInfoComponent,
    CreatorInfoComponent,
    OjamaInfoComponent,
    ExodiaInfoComponent,
    AscendedOneInfoComponent,
    AscendedTwoInfoComponent,
    AscendedThreeInfoComponent,
    PharaohOneInfoComponent,
    PharaohTwoInfoComponent,
    PharaohThreeInfoComponent,
    PharaohFourInfoComponent,
    PharaohFiveInfoComponent,
    RandomSmallInfoComponent,
    RandomBigInfoComponent,
    UpgradeDeckInfoComponent,
    MetronomeInfoComponent,
    DragonNeowComponent,
    NaturiaNeowComponent,
    SpellcasterNeowComponent,
    ToonNeowComponent,
    ZombieNeowComponent,
    AquaNeowComponent,
    FiendNeowComponent,
    MachineNeowComponent,
    WarriorNeowComponent,
    InsectNeowComponent,
    PlantNeowComponent,
    MegatypeNeowComponent,
    IncrementNeowComponent,
    CreatorNeowComponent,
    OjamaNeowComponent,
    ExodiaNeowComponent,
    AscendedOneNeowComponent,
    AscendedTwoNeowComponent,
    AscendedThreeNeowComponent,
    PharaohOneNeowComponent,
    PharaohTwoNeowComponent,
    PharaohThreeNeowComponent,
    PharaohFourNeowComponent,
    PharaohFiveNeowComponent,
    RandomSmallNeowComponent,
    RandomBigNeowComponent,
    UpgradeDeckNeowComponent,
    MetronomeNeowComponent,
    AllNeowComponent,
    AllRelicsComponent,
    AllPotionsComponent,
    AllCardsComponent,
    WarriorPotionsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    A11yModule,
    CdkStepperModule,
    CdkTableModule,
    MatAutocompleteModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatStepperModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatSortModule,
    MatTableModule,
    MatTabsModule,
    MatToolbarModule,
    MatTooltipModule,
    PortalModule,
  ],
  providers: [TopService, BundleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
