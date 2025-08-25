document.addEventListener('DOMContentLoaded', function() {
        // Inicializar MaterializeCSS
        M.updateTextFields();

        // Animação de entrada
        const formSection = document.querySelector('.form-section');
        const previewSection = document.querySelector('.preview-section');

        setTimeout(() => {
            formSection.classList.add('animate-in');
            previewSection.classList.add('animate-in');
        }, 100);

        // Funcionalidades mobile
        if (window.innerWidth <= 768) {
            initMobileFeatures();
        }
    });

    // Funcionalidades específicas para mobile
    function initMobileFeatures() {
        // Indicador de progresso da página
        window.addEventListener('scroll', updateProgressBar);

        // Auto-hide do FAB quando o formulário está visível
        const observer = new IntersectionObserver((entries) => {
            const fab = document.querySelector('.floating-cta');
            entries.forEach(entry => {
                if (entry.isIntersecting) {
                    fab.style.opacity = '0';
                    fab.style.pointerEvents = 'none';
                } else {
                    fab.style.opacity = '1';
                    fab.style.pointerEvents = 'auto';
                }
            });
        });

        const formSection = document.querySelector('.form-section');
        if (formSection) {
            observer.observe(formSection);
        }

        // Animação do indicador de rolagem
        animateScrollIndicator();

        // Auto-hide do scroll indicator após rolagem
        let scrollTimer;
        window.addEventListener('scroll', () => {
            const scrollIndicator = document.querySelector('.scroll-indicator');
            if (scrollIndicator && window.scrollY > 100) {
                scrollIndicator.style.opacity = '0';
                scrollIndicator.style.transform = 'translateY(-20px)';
            }
        });
    }

    // Atualizar barra de progresso
    function updateProgressBar() {
        const winScroll = document.body.scrollTop || document.documentElement.scrollTop;
        const height = document.documentElement.scrollHeight - document.documentElement.clientHeight;
        const scrolled = (winScroll / height) * 100;
        document.getElementById('progressBar').style.width = scrolled + '%';
    }

    // Scroll suave para o formulário
    function scrollToForm() {
        const formSection = document.querySelector('.form-section');
        formSection.scrollIntoView({
            behavior: 'smooth',
            block: 'start'
        });
    }

    // Animação do indicador de rolagem
    function animateScrollIndicator() {
        const arrow = document.querySelector('.scroll-arrow i');
        if (arrow) {
            setInterval(() => {
                arrow.style.transform = 'translateY(5px)';
                setTimeout(() => {
                    arrow.style.transform = 'translateY(0)';
                }, 500);
            }, 1500);
        }
    }

    // Vibração no mobile (se suportado)
    function vibrateOnAction() {
        if (navigator.vibrate) {
            navigator.vibrate(50);
        }
    }

    // Adicionar vibração aos botões principais
    document.addEventListener('click', function(e) {
        if (e.target.closest('.btn-cadastro') || e.target.closest('.fab-cadastro')) {
            vibrateOnAction();
        }
    });