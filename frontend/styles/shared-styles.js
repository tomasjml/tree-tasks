// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';

const $_documentContainer = document.createElement('template');

$_documentContainer.innerHTML = `
<custom-style>
<style>
        html {
      --lumo-base-color: #483B32;
      --lumo-primary-color: #74A57F;
      --lumo-error-color: #DB504A;
      --lumo-success-color: #FFBF00;
      --lumo-body-text-color: #F0E2E7;
      --lumo-primary-text-color: #74A57F;
      --lumo-error-text-color: #DB504A;
      --lumo-success-text-color: #FFBF00;
      --lumo-space-xxxl: 9.5rem;
      --lumo-space-xxl:3.0rem;
      --lumo-space-xl: 1.875rem;
      --lumo-space-l: 1.25rem;
      --lumo-space-m: 0.625rem;
      --lumo-space-s: 0.3125rem;
      --lumo-space-xs: 0.1875rem;

    }

    [theme~="dark"] {
      --lumo-base-color: #483B32;
      --lumo-primary-color: #74A57F;
      --lumo-error-color: #DB504A;
      --lumo-success-color: #FFBF00;
      --lumo-body-text-color: #F0E2E7;
      --lumo-primary-text-color: #74A57F;
      --lumo-error-text-color: #DB504A;
      --lumo-success-text-color: #FFBF00;

    }

</style>
</custom-style>


`;

document.head.appendChild($_documentContainer.content);
