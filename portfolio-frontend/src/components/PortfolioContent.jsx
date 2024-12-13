import React from 'react';
import PortfolioView from './PortfolioView';
import GainsView from './GainsView';
import LossView from './LossView';

function PortfolioContent({ view, portfolio, totals }) {
  const views = {
    portfolio: <PortfolioView portfolio={portfolio} />,
    gains: <GainsView portfolio={portfolio} totalGains={totals.gains} />,
    losses: <LossView portfolio={portfolio} totalLosses={totals.losses} />
  };

  return views[view] || views.portfolio;
}

export default PortfolioContent; 