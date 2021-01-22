// eagerly import theme styles so as we can override them
import '@vaadin/vaadin-lumo-styles/all-imports';
import '@vaadin/vaadin-grid/all-imports';


const $_documentContainer = document.createElement('template');


$_documentContainer.innerHTML = `
<custom-style>
<style>
        html {
      --lumo-base-color: #483B32;
      --lumo-base-color-darker: #3D3329;
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
      --lumo-primary-color-darker : #496F52;
      --lumo-error-color: #DB504A;
      --lumo-success-color: #FFBF00;
      --lumo-body-text-color: #F0E2E7;
      --lumo-primary-text-color: #74A57F;
      --lumo-error-text-color: #DB504A;
      --lumo-success-text-color: #FFBF00;

    }
    
    *::-webkit-scrollbar {
      width: 12px;
      height: 15px;
    }
    
    *::-webkit-scrollbar-track-piece  {
      background-color: var(--lumo-base-color-darker);
      border-radius: 5px;
    }
    
    *::-webkit-scrollbar-thumb {
      height: 30p#grid-wrappex;
      background-color: var(--lumo-primary-color);
      border-radius: 5px;
      -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
    }

</style>
</custom-style>

<dom-module id="vaadin-grid" theme-for="vaadin-grid">
    <template>
        <style>
             *::-webkit-scrollbar {
                  width: 12px;
                  height: 15px;
                }
                
                *::-webkit-scrollbar-track-piece  {
                  background-color: var(--lumo-base-color-darker);
                  border-radius: 5px;
                }
                
                *::-webkit-scrollbar-thumb {
                  height: 30p#grid-wrappex;
                  background-color: var(--lumo-primary-color);
                  border-radius: 5px;
                  -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, .3);
                }
        </style>
    </template>
</dom-module>

`;

document.head.appendChild($_documentContainer.content);
